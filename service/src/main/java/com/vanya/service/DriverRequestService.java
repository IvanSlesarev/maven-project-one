package com.vanya.service;

import com.vanya.dto.cargo.CargoRequestCreateEditDto;
import com.vanya.dto.cargo.CargoRequestReadDto;
import com.vanya.dto.driverRequest.DriverRequestCreateEditDto;
import com.vanya.dto.driverRequest.DriverRequestReadDto;

import java.util.List;
import java.util.Optional;

public interface DriverRequestService {
    List<DriverRequestReadDto> findAll();

    Optional<DriverRequestReadDto> findById(Integer id);

    DriverRequestReadDto create(DriverRequestCreateEditDto driverRequestCreateEditDto);

    Optional<DriverRequestReadDto> update(Integer id, DriverRequestCreateEditDto driverRequestCreateEditDto);

    void delete(Integer id);
}
