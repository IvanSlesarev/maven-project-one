package com.vanya.repository;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.DriverRequest;
import com.vanya.entity.DriverRequestStatus;
import com.vanya.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverRequestRepository extends RepositoryBase<Integer, DriverRequest> {

    public DriverRequestRepository() {
        super(DriverRequest.class);
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
