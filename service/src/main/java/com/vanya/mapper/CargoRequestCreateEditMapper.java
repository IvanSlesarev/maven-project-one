package com.vanya.mapper;

import com.vanya.dto.cargo.CargoRequestCreateEditDto;
import com.vanya.entity.CargoRequest;
import org.springframework.stereotype.Component;

@Component
public class CargoRequestCreateEditMapper implements Mapper <CargoRequestCreateEditDto, CargoRequest> {
    @Override
    public CargoRequest map(CargoRequestCreateEditDto object) {
        CargoRequest cargoRequest = new CargoRequest();
        copy(object, cargoRequest);
        return cargoRequest;
    }

    @Override
    public CargoRequest map(CargoRequestCreateEditDto fromObject, CargoRequest toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CargoRequestCreateEditDto object, CargoRequest cargoRequest) {
        cargoRequest.setUser(object.getUser());
        cargoRequest.setStartPoint(object.getStartPoint());
        cargoRequest.setEndPoint(object.getEndPoint());
        cargoRequest.setDescription(object.getDescription());
        cargoRequest.setWeight(object.getWeight());
        cargoRequest.setVolume(object.getVolume());
    }
}
