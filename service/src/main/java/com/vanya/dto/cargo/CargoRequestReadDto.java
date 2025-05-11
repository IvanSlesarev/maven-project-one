package com.vanya.dto.cargo;

import com.vanya.entity.User;
import lombok.Value;

@Value
public class CargoRequestReadDto {

    Integer id;
    User user;
    String startPoint;
    String endPoint;
    String description;
    Integer weight;
    Integer volume;
}
