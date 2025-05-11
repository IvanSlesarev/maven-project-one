package com.vanya.service.impl;

import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.dto.user.UserReadDto;
import com.vanya.entity.User;
import com.vanya.mapper.UserCreateEditMapper;
import com.vanya.mapper.UserReadMapper;
import com.vanya.repository.UserRepository;
import com.vanya.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    @Override
    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @Override
    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Override
    public UserReadDto create(UserCrateEditDto userCrateEditDto) {
        return Optional.of(userCrateEditDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<UserReadDto> update(Integer id, UserCrateEditDto userCrateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCrateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
