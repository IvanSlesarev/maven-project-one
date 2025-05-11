package com.vanya.dto.user;

import com.vanya.entity.Role;
import lombok.Value;

@Value
public class UserCrateEditDto {
    String username;
    String password;
    Role role;
    String info;
    String legalName;
}
