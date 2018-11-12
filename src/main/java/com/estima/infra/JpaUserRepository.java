package com.estima.infra;

import com.estima.domain.User;
import com.estima.domain.UserId;
import com.estima.domain.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> get(UserId userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> asList() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
