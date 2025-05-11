package com.vanya.service;

import com.vanya.dto.vehicle.VehicleCreateEditDto;
import com.vanya.dto.vehicle.VehicleReadDto;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<VehicleReadDto> findAll();

    Optional<VehicleReadDto> findById(Integer id);

    VehicleReadDto create(VehicleCreateEditDto vehicleCrateEditDto);

    Optional<VehicleReadDto> update(Integer id, VehicleCreateEditDto vehicleCreateEditDto);

    void delete(Integer id);
}
