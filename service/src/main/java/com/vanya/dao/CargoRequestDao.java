package com.vanya.dao;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.CargoRequestStatus;
import com.vanya.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class CargoRequestDao extends RepositoryBase<Integer, CargoRequest> {

    public CargoRequestDao(EntityManager entityManager) {
        super(CargoRequest.class, entityManager);
    }

    public List<CargoRequest> findByStatus(CargoRequestStatus status) {
        return entityManager.createQuery("FROM CargoRequest WHERE CargoRequestStatus = :status", CargoRequest.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<CargoRequest> findByUser(User user) {
        return entityManager.createQuery("FROM CargoRequest WHERE user = :user", CargoRequest.class)
                .setParameter("user", user)
                .getResultList();
    }
}
