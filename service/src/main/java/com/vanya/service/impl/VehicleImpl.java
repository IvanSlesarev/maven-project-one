package com.vanya.service.impl;

import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.user.UserReadDto;
import com.vanya.dto.vehicle.VehicleCreateEditDto;
import com.vanya.dto.vehicle.VehicleReadDto;
import com.vanya.mapper.VehicleCreateEditMapper;
import com.vanya.mapper.VehicleReadMapper;
import com.vanya.repository.VehicleRepository;
import com.vanya.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class VehicleImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleReadMapper vehicleReadMapper;
    private final VehicleCreateEditMapper vehicleCreateEditMapper;

    @Override
    public List<VehicleReadDto> findAll() {
        return vehicleRepository.findAll().stream()
                .map(vehicleReadMapper::map)
                .toList();
    }

    @Override
    public Optional<VehicleReadDto> findById(Integer id) {
        return vehicleRepository.findById(id)
                .map(vehicleReadMapper::map);
    }

    @Override
    public VehicleReadDto create(VehicleCreateEditDto vehicleCreateEditDto) {
        return Optional.of(vehicleCreateEditDto)
                .map(vehicleCreateEditMapper::map)
                .map(vehicleRepository::save)
                .map(vehicleReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<VehicleReadDto> update(Integer id, VehicleCreateEditDto vehicleCreateEditDto) {
        return vehicleRepository.findById(id)
                .map(entity -> vehicleCreateEditMapper.map(vehicleCreateEditDto, entity))
                .map(vehicleRepository::saveAndFlush)
                .map(vehicleReadMapper::map);
    }

    @Override
    public void delete(Integer id) {
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }
}
