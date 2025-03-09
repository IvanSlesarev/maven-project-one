package com.vanya.dao;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.DriverRequest;
import com.vanya.entity.DriverRequestStatus;
import com.vanya.entity.User;
import org.hibernate.Session;

import java.util.List;

public class DriverRequestDao extends RepositoryBase<Integer, DriverRequest> {

    public DriverRequestDao(Session session) {
        super(DriverRequest.class, session);
    }

    public List<DriverRequest> findByStatus(DriverRequestStatus status) {
        return entityManager.createQuery("FROM DriverRequest WHERE status = :status", DriverRequest.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<DriverRequest> findByUser(User user) {
        return entityManager.createQuery("FROM DriverRequest WHERE user = :user", DriverRequest.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<DriverRequest> findByCargoRequest(CargoRequest cargoRequest) {
        return entityManager.createQuery("FROM DriverRequest WHERE cargo = :cargo", DriverRequest.class)
                .setParameter("cargo", cargoRequest)
                .getResultList();
    }
}
