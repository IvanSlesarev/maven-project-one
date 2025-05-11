package com.vanya.service;

import com.vanya.dto.cargo.CargoRequestCreateEditDto;
import com.vanya.dto.cargo.CargoRequestReadDto;

import java.util.List;
import java.util.Optional;

public interface CargoRequestService {

    List<CargoRequestReadDto> findAll();

    Optional<CargoRequestReadDto> findById(Integer id);

    CargoRequestReadDto create(CargoRequestCreateEditDto cargoRequestCreateEditDto);

    Optional<CargoRequestReadDto> update(Integer id, CargoRequestCreateEditDto cargoRequestCreateEditDto);

    void delete(Integer id);
}
