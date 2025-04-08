package com.vanya.repository;

import com.vanya.entity.User;
import com.vanya.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findAllByUser(User user);
}
