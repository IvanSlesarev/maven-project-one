package com.vanya.dto.cargo;

import com.vanya.entity.User;
import lombok.Value;

@Value
public class CargoRequestCreateEditDto {
    User user;
    String startPoint;
    String endPoint;
    String description;
    Integer weight;
    Integer volume;
}
