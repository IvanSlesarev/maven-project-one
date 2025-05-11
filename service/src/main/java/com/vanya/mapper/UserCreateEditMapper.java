package com.vanya.mapper;

import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.user.UserReadDto;
import com.vanya.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCrateEditDto, User> {

    @Override
    public User map(UserCrateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCrateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCrateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setRole(object.getRole());
        user.setInfo(object.getInfo());
        user.setLegalName(object.getLegalName());
    }
}
