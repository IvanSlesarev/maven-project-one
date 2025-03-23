package com.vanya.repository;

import com.vanya.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements com.vanya.repository.Repository<K, E> {

    private final Class<E> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    protected RepositoryBase(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        E entity = entityManager.find(clazz, id);
        if (entity != null) {
            entityManager.remove(entity);
            entityManager.flush();
        }
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
