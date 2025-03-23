package com.vanya.repository;

import com.vanya.entity.User;
import com.vanya.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepository extends RepositoryBase<Integer, Vehicle> {

    public VehicleRepository() {
        super(Vehicle.class);
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
