package com.vanya.service;

import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.user.UserReadDto;


import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserReadDto> findAll();

    Optional <UserReadDto> findById(Integer id);

    UserReadDto create(UserCrateEditDto userCrateEditDto);

    Optional <UserReadDto> update(Integer id, UserCrateEditDto userCrateEditDto);

    void delete(Integer id);
}
