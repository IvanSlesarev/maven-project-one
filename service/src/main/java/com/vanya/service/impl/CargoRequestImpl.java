package com.vanya.service.impl;

import com.vanya.dto.cargo.CargoRequestCreateEditDto;
import com.vanya.dto.cargo.CargoRequestReadDto;
import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.user.UserReadDto;
import com.vanya.entity.CargoRequest;
import com.vanya.mapper.CargoRequestCreateEditMapper;
import com.vanya.mapper.CargoRequestReadMapper;
import com.vanya.mapper.UserCreateEditMapper;
import com.vanya.mapper.UserReadMapper;
import com.vanya.repository.CargoRequestRepository;
import com.vanya.repository.UserRepository;
import com.vanya.service.CargoRequestService;
import com.vanya.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoRequestImpl implements CargoRequestService {
    private final CargoRequestRepository cargoRequestRepository;
    private final CargoRequestReadMapper cargoRequestReadMapper;
    private final CargoRequestCreateEditMapper cargoRequestCreateEditMapper;

    @Override
    public List<CargoRequestReadDto> findAll() {
        return cargoRequestRepository.findAll().stream()
                .map(cargoRequestReadMapper::map)
                .toList();
    }

    @Override
    public Optional<CargoRequestReadDto> findById(Integer id) {
        return cargoRequestRepository.findById(id)
                .map(cargoRequestReadMapper::map);
    }

    @Override
    public CargoRequestReadDto create(CargoRequestCreateEditDto cargoRequestCreateEditDto) {
        return Optional.of(cargoRequestCreateEditDto)
                .map(cargoRequestCreateEditMapper::map)
                .map(cargoRequestRepository::save)
                .map(cargoRequestReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<CargoRequestReadDto> update(Integer id, CargoRequestCreateEditDto cargoRequestCreateEditDto) {
        return cargoRequestRepository.findById(id)
                .map(entity -> cargoRequestCreateEditMapper.map(cargoRequestCreateEditDto, entity))
                .map(cargoRequestRepository::saveAndFlush)
                .map(cargoRequestReadMapper::map);
    }

    @Override
    public void delete(Integer id) {
        if (!cargoRequestRepository.existsById(id)) {
            throw new EntityNotFoundException("Cargo not found" + id);
        }
        cargoRequestRepository.deleteById(id);
    }
}
