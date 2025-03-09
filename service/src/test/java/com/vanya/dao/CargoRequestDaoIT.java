package com.vanya.dao;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.CargoRequestStatus;
import com.vanya.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CargoRequestDaoIT {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private CargoRequestDao cargoRequestDao;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        cargoRequestDao = new CargoRequestDao(session) {};
    }

    @AfterEach
    void tearDown() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }

        if (session != null) {
            session.close();
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void saveAndFindById() {
        User user = new User();
        user.setUsername("john_doe");

        session.persist(user);

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .startPoint("Start")
                .endPoint("End")
                .description("Test Cargo")
                .weight(100)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();

        cargoRequestDao.save(cargoRequest);

        Optional<CargoRequest> foundCargoRequest = cargoRequestDao.findById(cargoRequest.getId());

        assertTrue(foundCargoRequest.isPresent());
        assertEquals("Test Cargo", foundCargoRequest.get().getDescription());
    }

    @Test
    void findByStatus() {
        User user = new User();
        user.setUsername("john_doe");

        session.persist(user);

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .startPoint("Start")
                .endPoint("End")
                .description("Test Cargo")
                .weight(100)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();

        cargoRequestDao.save(cargoRequest);

        List<CargoRequest> cargoRequests = cargoRequestDao.findByStatus(CargoRequestStatus.PENDING);

        assertFalse(cargoRequests.isEmpty());
        assertEquals(CargoRequestStatus.PENDING, cargoRequests.get(0).getCargoRequestStatus());
    }

    @Test
    void findByUser() {
        User user = new User();
        user.setUsername("john_doe");

        session.persist(user);

        CargoRequest cargoRequest = CargoRequest.builder()
                .user(user)
                .startPoint("Start")
                .endPoint("End")
                .description("Test Cargo")
                .weight(100)
                .volume(10)
                .CargoRequestStatus(CargoRequestStatus.PENDING)
                .build();

        cargoRequestDao.save(cargoRequest);

        List<CargoRequest> cargoRequests = cargoRequestDao.findByUser(user);

        assertFalse(cargoRequests.isEmpty());
        assertEquals(user.getId(), cargoRequests.get(0).getUser().getId());
    }
}