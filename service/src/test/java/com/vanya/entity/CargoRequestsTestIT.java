package com.vanya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CargoRequestsTestIT {
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

        session.createQuery("DELETE FROM CargoRequests").executeUpdate();
        session.createQuery("DELETE FROM Route").executeUpdate();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();

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
    public void whenSaveCargoRequestThenCargoRequestIsSaved() {
        User user = User.builder()
                .username("unique_username_")
                .password("password")
                .role(Role.CARRIER)
                .info("contact info")
                .legalName("legal name")
                .build();

        Route route = Route.builder()
                .user(user)
                .startPoint("Start Point")
                .endPoint("End Point")
                .price(100.0)
                .status(RouteStatus.OPEN)
                .build();

        CargoRequests cargoRequest = CargoRequests.builder()
                .user(user)
                .route(route)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();

        session.persist(user);
        session.persist(route);
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequests foundCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());
        assertThat(foundCargoRequest).isNotNull();
        assertThat(foundCargoRequest.getDescription()).isEqualTo("Cargo Description");
        assertThat(foundCargoRequest.getUser().getUsername()).startsWith("unique_username_");
    }

    @Test
    public void whenFindCargoRequestByIdThenCargoRequestIsReturned() {
        User user = User.builder()
                .username("unique_username_")
                .password("password")
                .role(Role.CARRIER)
                .info("contact info")
                .legalName("legal name")
                .build();
        session.persist(user);

        Route route = Route.builder()
                .user(user)
                .startPoint("Start Point")
                .endPoint("End Point")
                .price(100.0)
                .status(RouteStatus.OPEN)
                .build();
        session.persist(route);

        CargoRequests cargoRequest = CargoRequests.builder()
                .user(user)
                .route(route)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequests foundCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());

        assertThat(foundCargoRequest).isNotNull();
        assertThat(foundCargoRequest.getWeight()).isEqualTo(500);
    }

    @Test
    public void whenUpdateCargoRequestThenCargoRequestIsUpdated() {
        User user = User.builder()
                .username("unique_username_")
                .password("password")
                .role(Role.CARRIER)
                .info("contact info")
                .legalName("legal name")
                .build();
        session.persist(user);

        Route route = Route.builder()
                .user(user)
                .startPoint("Start Point")
                .endPoint("End Point")
                .price(100.0)
                .status(RouteStatus.OPEN)
                .build();
        session.persist(route);

        CargoRequests cargoRequest = CargoRequests.builder()
                .user(user)
                .route(route)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        CargoRequests foundCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());
        foundCargoRequest.setWeight(600);
        session.merge(foundCargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequests updatedCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());
        assertThat(updatedCargoRequest.getWeight()).isEqualTo(600);
    }

    @Test
    public void whenDeleteCargoRequestThenCargoRequestIsDeleted() {
        User user = User.builder()
                .username("unique_username_")
                .password("password")
                .role(Role.CARRIER)
                .info("contact info")
                .legalName("legal name")
                .build();
        session.persist(user);
        Route route = Route.builder()
                .user(user)
                .startPoint("Start Point")
                .endPoint("End Point")
                .price(100.0)
                .status(RouteStatus.OPEN)
                .build();
        session.persist(route);
        CargoRequests cargoRequest = CargoRequests.builder()
                .user(user)
                .route(route)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        CargoRequests foundCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());
        session.remove(foundCargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequests deletedCargoRequest = session.find(CargoRequests.class, cargoRequest.getId());
        assertThat(deletedCargoRequest).isNull();
    }
}
