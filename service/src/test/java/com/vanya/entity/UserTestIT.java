package com.vanya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


class UserTestIT {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setUpAll() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    @BeforeEach
    public void setUp() {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    @AfterAll
    public static void tearDownAll() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void whenSaveUserThenUserIsSaved() {
        User user = User.builder()
                .username("MegaPerezovBy")
                .password("55555")
                .role(Role.CARRIER)
                .info("+375448909875")
                .legalName("Ivanov Ivan")
                .build();

        session.persist(user);
        session.getTransaction().commit();
        session.clear();

        User foundUser = session.find(User.class, user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("MegaPerezovBy");
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        User user = User.builder()
                .username("PerezovBy")
                .password("123467")
                .role(Role.CARRIER)
                .info("+3754488888888")
                .legalName("Sidorov Petr")
                .build();
        session.persist(user);
        session.getTransaction().commit();
        session.clear();

        User foundUser = session.find(User.class, user.getId());

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("PerezovBy");
    }

    @Test
    public void whenUpdateUserThenUserIsUpdated() {
        User user = User.builder()
                .username("Gruz24")
                .password("5678")
                .role(Role.CARRIER)
                .info("+375449999999")
                .legalName("Petrov Oleg")
                .build();
        session.persist(user);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        User foundUser = session.find(User.class, user.getId());
        foundUser.setUsername("updatedUsername");
        session.merge(foundUser);
        session.getTransaction().commit();
        session.clear();

        User updatedUser = session.find(User.class, user.getId());
        assertThat(updatedUser.getUsername()).isEqualTo("updatedUsername");
    }

    @Test
    public void whenDeleteUserThenUserIsDeleted() {
        User user = User.builder()
                .username("user")
                .password("password")
                .role(Role.CARRIER)
                .info("info")
                .legalName("legal name")
                .build();
        session.persist(user);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        User foundUser = session.find(User.class, user.getId());
        session.remove(foundUser);
        session.getTransaction().commit();
        session.clear();

        User deletedUser = session.find(User.class, user.getId());
        assertThat(deletedUser).isNull();
    }
}