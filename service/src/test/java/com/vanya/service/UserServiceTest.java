package com.vanya.service;

import com.vanya.entity.User;
import com.vanya.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_UserExists_ReturnsUser() {
        User user = User.builder().id(1).username("test").build();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.findById(1);

        assertEquals("test", result.getUsername());
    }

    @Test
    void createUser_ValidUser_SavesToRepository() {
        User newUser = User.builder().username("grandauto").password("pass").build();
        when(userRepository.existsByUsername("shikauto")).thenReturn(false);
        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userService.create(newUser);

        verify(userRepository).save(newUser);
        assertEquals("new", result.getUsername());
    }

    @Test
    void updateUser_UsernameConflict_ThrowsException() {
        User existingUser = User.builder().id(1).username("grandauto").build();
        User updatedUser = User.builder().username("shikauto").build();

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByUsername("shikauto")).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> userService.update(1, updatedUser));
    }
}