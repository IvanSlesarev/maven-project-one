package com.vanya.dao;

import com.vanya.entity.Role;
import com.vanya.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RequiredArgsConstructor
class UserDaoIT {
    private SessionFactory sessionFactory;
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        userDao = UserDao.getInstance();
    }

    @Test
    void findAllByUserName() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user1 = new User();
            user1.setUsername("john_doe");
            session.save(user1);

            User user2 = new User();
            user2.setUsername("jane_doe");
            session.save(user2);
            session.getTransaction().commit();

            List<User> users = userDao.findAllByUserName(session, "john_doe");

            assertEquals(1, users.size());
            assertEquals("john_doe", users.get(0).getUsername());
        }
    }

    @Test
    void findAllByRole() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user1 = new User();
            user1.setUsername("john_doe");
            user1.setRole(Role.ADMIN);
            session.save(user1);

            User user2 = new User();
            user2.setUsername("jane_doe");
            user2.setRole(Role.CARRIER);
            session.save(user2);

            session.getTransaction().commit();

            List<User> users = userDao.findAllByRole(session, Role.ADMIN);

            assertEquals(1, users.size());
            assertEquals(Role.ADMIN, users.get(0).getRole());
        }
    }
}