package com.vanya.repository;

import com.vanya.entity.Role;
import com.vanya.entity.User;
import com.vanya.spring.config.ApplicationConfiguration;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTestIT {

    protected static EntityManager entityManager;
    protected static AnnotationConfigApplicationContext context;
    private UserRepository userRepository;

    @BeforeAll
    static void setupApplicationContext() {
        context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    @BeforeEach
    void beginTransaction() {
        entityManager = context.getBean(EntityManager.class);
        entityManager.getTransaction().begin();
        userRepository = context.getBean(UserRepository.class);
    }

    @AfterEach
    void closeTransaction() {
        entityManager.getTransaction().rollback();
    }

    @AfterAll
    static void closeApplicationContext() {
        context.close();
    }
    @Test
    void save () {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.CARRIER);
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();
        Optional<User> actualResult = userRepository.findById(user.getId());
        assertTrue(actualResult.isPresent());
        assertEquals(user.getUsername(), actualResult.get().getUsername());
    }
    @Test
    void findById() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.CARRIER);
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    void update() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.CARRIER);
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        user.setUsername("updatedUsername");
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("updatedUsername", updatedUser.get().getUsername());
    }

    @Test
    void delete() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.CARRIER);
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        // userRepository.delete(user);

        entityManager.flush();
        entityManager.clear();

        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}