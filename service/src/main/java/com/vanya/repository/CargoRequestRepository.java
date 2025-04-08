package com.vanya.repository;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.CargoRequestStatus;
import com.vanya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRequestRepository extends JpaRepository<CargoRequest, Integer> {
    List<CargoRequest> findByStatus(CargoRequestStatus status);
    List<CargoRequest> findByUser(User user);
}
