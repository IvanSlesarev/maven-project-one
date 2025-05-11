package com.vanya.dto.user;

import com.vanya.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilterDto {
  String username;
  Role role;
}
