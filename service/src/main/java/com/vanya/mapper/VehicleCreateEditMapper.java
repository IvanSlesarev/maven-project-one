package com.vanya.mapper;

import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.vehicle.VehicleCreateEditDto;
import com.vanya.entity.User;
import com.vanya.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleCreateEditMapper implements Mapper <VehicleCreateEditDto, Vehicle> {

    @Override
    public Vehicle map (VehicleCreateEditDto object) {
        Vehicle vehicle = new Vehicle();
        copy(object, vehicle);
        return vehicle;
    }

    @Override
    public Vehicle map(VehicleCreateEditDto fromObject, Vehicle toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy (VehicleCreateEditDto object, Vehicle vehicle) {
        vehicle.setId(object.getId());
        vehicle.setUser(object.getUser());
        vehicle.setVehicleNumber(object.getVehicleNumber());
        vehicle.setTrailerNumber(object.getTrailerNumber());
        vehicle.setDriverName(object.getDriverName());
        vehicle.setInfo(object.getInfo());
    }
}

