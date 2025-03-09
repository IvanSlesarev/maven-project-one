package com.vanya.dao;

import com.vanya.entity.CargoRequest;
import com.vanya.entity.DriverRequest;
import com.vanya.entity.DriverRequestStatus;
import com.vanya.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverRequestDaoIT {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private DriverRequestDao driverRequestDao;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        driverRequestDao = new DriverRequestDao(session);
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
    void findByStatus() {
        User user = new User();
        user.setUsername("john_doe");

        CargoRequest cargoRequest = new CargoRequest();
        cargoRequest.setDescription("Test Cargo");
        cargoRequest.setUser(user);

        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setUser(user);
        driverRequest.setCargo(cargoRequest);
        driverRequest.setPrice(100.0);
        driverRequest.setStatus(DriverRequestStatus.OPEN);

        session.persist(user);
        session.persist(cargoRequest);
        session.persist(driverRequest);
        transaction.commit();

        List<DriverRequest> driverRequests = driverRequestDao.findByStatus(DriverRequestStatus.OPEN);

        assertFalse(driverRequests.isEmpty());
        assertEquals(DriverRequestStatus.OPEN, driverRequests.get(0).getStatus());
    }

    @Test
    void findByUser() {
        User user = new User();
        user.setUsername("john_doe");

        CargoRequest cargoRequest = new CargoRequest();
        cargoRequest.setDescription("Test Cargo");
        cargoRequest.setUser(user);

        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setUser(user);
        driverRequest.setCargo(cargoRequest);
        driverRequest.setPrice(100.0);
        driverRequest.setStatus(DriverRequestStatus.OPEN);

        session.persist(user);
        session.persist(cargoRequest);
        session.persist(driverRequest);
        transaction.commit();

        List<DriverRequest> driverRequests = driverRequestDao.findByUser(user);

        assertFalse(driverRequests.isEmpty());
        assertEquals(user.getId(), driverRequests.get(0).getUser().getId());
    }

    @Test
    void findByCargoRequest() {
        User user = new User();
        user.setUsername("john_doe");

        CargoRequest cargoRequest = new CargoRequest();
        cargoRequest.setDescription("Test Cargo");
        cargoRequest.setUser(user);

        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setUser(user);
        driverRequest.setCargo(cargoRequest);
        driverRequest.setPrice(100.0);
        driverRequest.setStatus(DriverRequestStatus.OPEN);

        session.persist(user);
        session.persist(cargoRequest);
        session.persist(driverRequest);
        transaction.commit();

        List<DriverRequest> driverRequests = driverRequestDao.findByCargoRequest(cargoRequest);

        assertFalse(driverRequests.isEmpty());
        assertEquals(cargoRequest.getId(), driverRequests.get(0).getCargo().getId());
    }
}