package com.vanya.dto.user;

import com.vanya.entity.Role;
import lombok.Value;

@Value
public class UserReadDto {

    Integer id;
    String username;
    Role role;
    String info;
    String legalName;
}
