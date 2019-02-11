package com.estima.infra.jpa;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import com.estima.domain.BuildingSelection;
import com.estima.domain.Position;
import com.estima.interfaces.rest.request.BuildingLocateRequest;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JpaBuildingRepository implements BuildingRepository {

    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<Building> get(Long id) {
        return Optional.ofNullable(entityManager.find(Building.class, id, LockModeType.OPTIMISTIC));
    }

    @Override
    @Transactional(readOnly = true)
    // todo: move to separate interface?
    public BuildingSelection query(BuildingSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cqb = cb.createQuery(Building.class);
        CriteriaQuery<Long> cqt = cb.createQuery(Long.class);
        Root<Building> buildingRoot = cqb.from(Building.class);
        Root<Building> count = cqt.from(Building.class);
        Predicate predicate = cb.and();
        Predicate query;

        Join<Building, Position> joinedPositions = buildingRoot.join("positions", JoinType.LEFT);
        count.join("positions", JoinType.LEFT);

        if (request.query() != null) {
            query = cb.or(
                    cb.like(cb.lower(buildingRoot.get("name")), "%" + request.query().trim().toLowerCase() + "%"),
                    cb.like(cb.lower(buildingRoot.get("address")), "%" + request.query().trim().toLowerCase() + "%")
            );
            predicate = cb.and(predicate, query);
        }

        if (!request.statuses().isEmpty()) {
            predicate = cb.and(predicate, buildingRoot.get("status").in(request.statuses()));
        }

        if (!request.authorsIds().isEmpty()) {
            predicate = cb.and(predicate, buildingRoot.get("authorId").in(request.authorsIds()));
        }

        if (request.lastUpdatedFrom() != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(buildingRoot.get("latestPositionDateUpdated"), request.lastUpdatedFrom()));
        }

        if (!request.dealersIds().isEmpty()) {
            predicate = cb.and(predicate, joinedPositions.get("dealer").get("id").in(request.dealersIds()));
        }

        cqb.select(buildingRoot);
        cqb.distinct(true);
        cqb.where(predicate);
        cqb.orderBy(new StringOrder(buildingRoot, request.sort(), request.order()));

        List<Building> buildingList = entityManager.createQuery(cqb)
                .setMaxResults(request.max())
                .setFirstResult(request.offset())
                .getResultList();

        cqt.select(cb.countDistinct(count));
        cqt.where(predicate);

        Long buildingTotal = entityManager.createQuery(cqt).getSingleResult();

        return new BuildingSelection(buildingList, buildingTotal.intValue());
    }

    @Override
    public Optional<Building> getPositionBuilding(Long positionId) {
        EntityGraph<Position> entityGraph = entityManager.createEntityGraph(Position.class);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        entityGraph.addAttributeNodes("building");
        Position position = entityManager.find(Position.class, positionId, properties);
        return Optional.ofNullable(position.building());
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingSelection locate(BuildingLocateRequest request) {
        Query qb = entityManager.createNativeQuery("SELECT b.* FROM building b WHERE ST_DWithin(st_geogfromtext(b.location), Geography(ST_MakePoint(:lon, :lat)), :radius)", Building.class);

//        qb.setMaxResults(1000);  // todo: is restriction required?
        qb.setParameter("lon", request.latLng().lng());
        qb.setParameter("lat", request.latLng().lat());
        qb.setParameter("radius", request.radius());

        List<Building> buildingList = qb.getResultList();

        return new BuildingSelection(buildingList, buildingList.size());
    }

    @Override
    @Transactional
    public void add(Building building) {
        entityManager.persist(building);
        // fixme: remove if building isn't immediately used
        refresh(building);
    }

    @Override
    @Transactional
    public void update(Building building) {
        entityManager.merge(building);
        // fixme: remove if building isn't immediately used
        refresh(building);
    }

    // to initialize client and project objects
    private void refresh(Building building) {
        entityManager.flush();
        entityManager.refresh(building);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    static class StringOrder extends OrderImpl {

        private static final String ASC = "asc";
        private static final String DESC = "desc";

        StringOrder(From from, String sortBy, String order) {
            this(from.get(sortBy), order.equalsIgnoreCase(ASC));
        }

        StringOrder(Expression<?> expression, boolean ascending) {
            super(expression, ascending);
        }
    }
}
