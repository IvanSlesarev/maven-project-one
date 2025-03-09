package com.vanya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CargoRequestTestIT {
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

        session.createQuery("DELETE FROM CargoRequest").executeUpdate();
        session.createQuery("DELETE FROM DriverRequest").executeUpdate();
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

        DriverRequest driverRequest = DriverRequest.builder()
                .user(user)
                .price(100.0)
                .status(DriverRequestStatus.OPEN)
                .build();

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();

        session.persist(user);
        session.persist(driverRequest);
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequest foundCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());
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

        DriverRequest driverRequest = DriverRequest.builder()
                .user(user)
                .price(100.0)
                .status(DriverRequestStatus.OPEN)
                .build();
        session.persist(driverRequest);

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequest foundCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());

        assertThat(foundCargoRequest).isNotNull();
        assertThat(foundCargoRequest.getWeight()).isEqualTo(500);
    }

    @Test
    void whenUpdateCargoRequestThenCargoRequestIsUpdated() {
        User user = User.builder()
                .username("unique_username_")
                .password("password")
                .role(Role.CARRIER)
                .info("contact info")
                .legalName("legal name")
                .build();
        session.persist(user);

        DriverRequest driverRequest = DriverRequest.builder()
                .user(user)
                .price(100.0)
                .status(DriverRequestStatus.OPEN)
                .build();
        session.persist(driverRequest);

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        CargoRequest foundCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());
        foundCargoRequest.setWeight(600);
        session.merge(foundCargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequest updatedCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());
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
        DriverRequest driverRequest = DriverRequest.builder()
                .user(user)
                .price(100.0)
                .status(DriverRequestStatus.OPEN)
                .build();
        session.persist(driverRequest);
        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .description("Cargo Description")
                .weight(500)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();
        session.persist(cargoRequest);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        CargoRequest foundCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());
        session.remove(foundCargoRequest);
        session.getTransaction().commit();
        session.clear();

        CargoRequest deletedCargoRequest = session.find(CargoRequest.class, cargoRequest.getId());
        assertThat(deletedCargoRequest).isNull();
    }
}
