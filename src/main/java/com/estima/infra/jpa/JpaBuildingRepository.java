package com.estima.infra.jpa;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import com.estima.domain.BuildingSelection;
import com.estima.interfaces.rest.request.BuildingSearchRequest;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;
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
    public BuildingSelection query(BuildingSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cqb = cb.createQuery(Building.class);
        CriteriaQuery<Long> cqt = cb.createQuery(Long.class);
        Root<Building> buildingRoot = cqb.from(Building.class);
        Root<Building> count = cqt.from(Building.class);
        Predicate predicate = cb.and();
        Predicate query;

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

//        buildingRoot.fetch("messages", JoinType.LEFT);
        cqb.select(buildingRoot);
        cqb.where(predicate);
        cqb.orderBy(new StringOrder(buildingRoot, request.sort(), request.order()));

        List<Building> buildingList = entityManager.createQuery(cqb)
                .setMaxResults(request.max())
                .setFirstResult(request.offset())
                .getResultList();

//        count.join("messages", JoinType.LEFT);
        cqt.select(cb.count(count));
        cqt.where(predicate);

        Long buildingTotal = entityManager.createQuery(cqt).getSingleResult();

        return new BuildingSelection(buildingList, buildingTotal.intValue());
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
