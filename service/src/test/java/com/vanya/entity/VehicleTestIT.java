package com.vanya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class VehicleTestIT {

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
    public void whenSaveVehicleThenVehicleIsSaved() {
        User user = User.builder()
                .username("Jhon Doree")
                .password("353667")
                .role(Role.CARRIER)
                .info("+375445676860")
                .legalName("Vladimir")
                .build();
        session.persist(user);

        Vehicle vehicle = Vehicle.builder()
                .user(user)
                .vehicleNumber("ABC123")
                .trailerNumber("XYZ456")
                .driverName("John Doe")
                .info("+375446789098")
                .build();

        session.persist(vehicle);
        session.getTransaction().commit();
        session.clear();

        Vehicle foundVehicle = session.find(Vehicle.class, vehicle.getId());
        assertThat(foundVehicle).isNotNull();
        assertThat(foundVehicle.getVehicleNumber()).isEqualTo("ABC123");
        assertThat(foundVehicle.getUser().getUsername()).isEqualTo("Jhon Doree");
    }

    @Test
    public void whenFindVehicleByIdThenVehicleIsReturned() {
        User user = User.builder()
                .username("Jhon Do")
                .password("353667")
                .role(Role.CARRIER)
                .info("+375445676860")
                .legalName("Vladimir")
                .build();
        session.persist(user);

        Vehicle vehicle = Vehicle.builder()
                .user(user)
                .vehicleNumber("ABC123")
                .trailerNumber("XYZ456")
                .driverName("John Doe")
                .info("Driver contact info")
                .build();
        session.persist(vehicle);
        session.getTransaction().commit();
        session.clear();

        Vehicle foundVehicle = session.find(Vehicle.class, vehicle.getId());

        assertThat(foundVehicle).isNotNull();
        assertThat(foundVehicle.getTrailerNumber()).isEqualTo("XYZ456");
    }

    @Test
    public void whenUpdateVehicleThenVehicleIsUpdated() {
        User user = User.builder()
                .username("Jhon Dore")
                .password("353667")
                .role(Role.CARRIER)
                .info("+375445676860")
                .legalName("Vladimir")
                .build();
        session.persist(user);

        Vehicle vehicle = Vehicle.builder()
                .user(user)
                .vehicleNumber("ABC123")
                .trailerNumber("XYZ456")
                .driverName("John Doe")
                .info("Driver contact info")
                .build();
        session.persist(vehicle);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        Vehicle foundVehicle = session.find(Vehicle.class, vehicle.getId());
        foundVehicle.setVehicleNumber("UPDATED123");
        session.merge(foundVehicle);
        session.getTransaction().commit();
        session.clear();

        Vehicle updatedVehicle = session.find(Vehicle.class, vehicle.getId());
        assertThat(updatedVehicle.getVehicleNumber()).isEqualTo("UPDATED123");
    }

    @Test
    public void whenDeleteVehicleThenVehicleIsDeleted() {
        User user = User.builder()
                .username("Jhon Doree")
                .password("353667")
                .role(Role.CARRIER)
                .info("+375445676860")
                .legalName("Vladimir")
                .build();
        session.persist(user);

        Vehicle vehicle = Vehicle.builder()
                .user(user)
                .vehicleNumber("ABC123")
                .trailerNumber("XYZ456")
                .driverName("John Doe")
                .info("Driver contact info")
                .build();
        session.persist(vehicle);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        Vehicle foundVehicle = session.find(Vehicle.class, vehicle.getId());
        session.remove(foundVehicle);
        session.getTransaction().commit();
        session.clear();

        Vehicle deletedVehicle = session.find(Vehicle.class, vehicle.getId());
        assertThat(deletedVehicle).isNull();
    }
}
