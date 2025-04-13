package com.vanya.controller;

import com.vanya.entity.User;
import com.vanya.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserById_ReturnsUser() throws Exception {
        User user = User.builder().id(1).username("grandauto").build();
        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("grandauto"));
    }

    @Test
    void deleteUser_NotExists_Returns404() throws Exception {

        doThrow(new EntityNotFoundException("User not found"))
                .when(userService).delete(999);

        mockMvc.perform(delete("/api/users/999"))
                .andExpect(status().isNotFound());
    }
}