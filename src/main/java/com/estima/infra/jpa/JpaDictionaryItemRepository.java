package com.estima.infra.jpa;

import com.estima.domain.DictionaryItem;
import com.estima.domain.DictionaryItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaDictionaryItemRepository implements DictionaryItemRepository {

    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<DictionaryItem> get(Long dictionaryId, Long id) {
        return Optional.ofNullable(entityManager.find(DictionaryItem.class, id));
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
