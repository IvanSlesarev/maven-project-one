package com.vanya.dao;

import com.vanya.entity.User;
import com.vanya.entity.Vehicle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class VehicleDao extends RepositoryBase<Integer, Vehicle> {

    public VehicleDao(EntityManager entityManager) {
        super(Vehicle.class, entityManager);
    }

    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.vehicleNumber = :vehicleNumber", Vehicle.class)
                .setParameter("vehicleNumber", vehicleNumber)
                .getResultStream()
                .findFirst();
    }

    public List<Vehicle> findAllByUser(User user) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.user = :user", Vehicle.class)
                .setParameter("user", user)
                .getResultList();
    }
}