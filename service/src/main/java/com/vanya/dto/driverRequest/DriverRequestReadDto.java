package com.vanya.dto.driverRequest;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.DriverRequestStatus;
import com.vanya.entity.User;
import lombok.Value;

@Value
public class DriverRequestReadDto {
    Integer id;
    CargoRequest cargo;
    User user;
    Double price;
    DriverRequestStatus status;
}
