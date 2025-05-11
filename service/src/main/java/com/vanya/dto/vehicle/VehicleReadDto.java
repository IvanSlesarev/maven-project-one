package com.vanya.dto.vehicle;

import com.vanya.entity.User;
import lombok.Value;

@Value
public class VehicleReadDto {
    Integer id;
    User user;
    String vehicleNumber;
    String trailerNumber;
    String driverName;
    String info;
}
