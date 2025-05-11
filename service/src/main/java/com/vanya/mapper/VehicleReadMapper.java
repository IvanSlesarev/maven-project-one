package com.vanya.mapper;

import com.vanya.dto.vehicle.VehicleReadDto;
import com.vanya.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleReadMapper implements Mapper <Vehicle, VehicleReadDto>{

    @Override
    public VehicleReadDto map(Vehicle object) {
        return new VehicleReadDto(
                object.getId(),
                object.getUser(),
                object.getVehicleNumber(),
                object.getTrailerNumber(),
                object.getDriverName(),
                object.getInfo()
        );
    }
}
