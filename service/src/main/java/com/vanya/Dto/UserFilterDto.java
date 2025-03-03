package com.vanya.Dto;

import com.vanya.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilterDto {
   public String username;
   public Role role;
}
