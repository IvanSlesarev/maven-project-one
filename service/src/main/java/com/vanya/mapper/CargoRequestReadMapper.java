package com.vanya.mapper;

import com.vanya.dto.cargo.CargoRequestReadDto;
import com.vanya.entity.CargoRequest;
import org.springframework.stereotype.Component;

@Component
public class CargoRequestReadMapper implements Mapper <CargoRequest, CargoRequestReadDto>{

    public CargoRequestReadDto map(CargoRequest object) {
        return new CargoRequestReadDto(
                object.getId(),
                object.getUser(),
                object.getStartPoint(),
                object.getEndPoint(),
                object.getDescription(),
                object.getWeight(),
                object.getVolume()
        );
    }
}
