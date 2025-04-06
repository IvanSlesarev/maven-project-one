package com.vanya.repository;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.DriverRequest;
import com.vanya.entity.DriverRequestStatus;
import com.vanya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRequestRepository extends JpaRepository<DriverRequest, Integer> {
    List<DriverRequest> findByStatus(DriverRequestStatus status);
    List<DriverRequest> findByUser(User user);
    List<DriverRequest> findByCargo(CargoRequest cargo);
}
