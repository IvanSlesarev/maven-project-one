package com.vanya.mapper;

import com.vanya.dto.driverRequest.DriverRequestReadDto;
import com.vanya.entity.DriverRequest;
import lombok.Value;

@Value
public class DriverRequestReadMapper implements Mapper<DriverRequest, DriverRequestReadDto> {

    @Override
    public DriverRequestReadDto map(DriverRequest object) {
        return new DriverRequestReadDto(
                object.getId(),
                object.getCargo(),
                object.getUser(),
                object.getPrice(),
                object.getStatus()
        );
    }
}

