package com.vanya.service.impl;

import com.vanya.dto.driverRequest.DriverRequestCreateEditDto;
import com.vanya.dto.driverRequest.DriverRequestReadDto;
import com.vanya.mapper.DriverRequestCreateEditMapper;
import com.vanya.mapper.DriverRequestReadMapper;
import com.vanya.repository.DriverRequestRepository;
import com.vanya.service.DriverRequestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverRequestImpl implements DriverRequestService {
    private final DriverRequestRepository driverRequestRepository;
    private final DriverRequestReadMapper driverRequestReadMapper;
    private final DriverRequestCreateEditMapper driverRequestCreateEditMapper;

    @Override
    public List<DriverRequestReadDto> findAll() {
        return driverRequestRepository.findAll().stream()
                .map(driverRequestReadMapper::map)
                .toList();
    }

    @Override
    public Optional<DriverRequestReadDto> findById(Integer id) {
        return driverRequestRepository.findById(id)
                .map(driverRequestReadMapper::map);
    }

    @Override
    public DriverRequestReadDto create(DriverRequestCreateEditDto driverRequestCreateEditDto) {
        return Optional.of(driverRequestCreateEditDto)
                .map(driverRequestCreateEditMapper::map)
                .map(driverRequestRepository::save)
                .map(driverRequestReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<DriverRequestReadDto> update(Integer id, DriverRequestCreateEditDto driverRequestCreateEditDto) {
        return driverRequestRepository.findById(id)
                .map(entity -> driverRequestCreateEditMapper.map(driverRequestCreateEditDto, entity))
                .map(driverRequestRepository::saveAndFlush)
                .map(driverRequestReadMapper::map);
    }

    @Override
    public void delete(Integer id) {
        if (!driverRequestRepository.existsById(id)) {
            throw new EntityNotFoundException("Cargo not found" + id);
        }
        driverRequestRepository.deleteById(id);
    }
}
