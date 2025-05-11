package com.vanya.mapper;

import com.vanya.dto.user.UserReadDto;
import com.vanya.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {


    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getRole(),
                object.getInfo(),
                object.getLegalName()
        );
    }
}
