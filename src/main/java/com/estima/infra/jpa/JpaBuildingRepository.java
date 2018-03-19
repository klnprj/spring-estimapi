package com.estima.infra.jpa;

import com.estima.domain.Building;
import com.estima.domain.BuildingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class JpaBuildingRepository implements BuildingRepository {

    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<Building> get(Long id) {
        return Optional.ofNullable(entityManager.find(Building.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Building> asList() {
        return entityManager.createQuery("FROM Building b", Building.class).getResultList();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
