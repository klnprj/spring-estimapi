package com.estima.infra.jpa;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.Collection;
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
    public Collection<Building> asList() {
        return entityManager.createQuery("FROM Building b", Building.class).getResultList();
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
}
