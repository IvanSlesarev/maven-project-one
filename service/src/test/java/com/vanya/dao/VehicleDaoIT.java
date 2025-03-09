package com.vanya.dao;

import com.vanya.entity.User;
import com.vanya.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehicleDaoIT {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private VehicleDao vehicleDao;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        vehicleDao = new VehicleDao(session);
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
        user.setId(1L);
        user.setUsername("John Doe");
        session.save(user);

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleNumber("ABC123");
        vehicle.setTrailerNumber("XYZ987");
        vehicle.setDriverName("John Doe");
        vehicle.setInfo("Contact: 123-456-7890");

        vehicleDao.save(vehicle);

        Optional<Vehicle> foundVehicle = vehicleDao.findById(vehicle.getId(), Collections.emptyMap());

        assertTrue(foundVehicle.isPresent());
        assertEquals("ABC123", foundVehicle.get().getVehicleNumber());
    }

    @Test
    void update() {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        session.save(user);

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleNumber("ABC123");
        vehicle.setTrailerNumber("XYZ987");
        vehicle.setDriverName("John Doe");
        vehicle.setInfo("Contact: 123-456-7890");

        vehicleDao.save(vehicle);

        vehicle.setVehicleNumber("NEW123");
        vehicleDao.update(vehicle);

        Optional<Vehicle> updatedVehicle = vehicleDao.findById(vehicle.getId(), Collections.emptyMap());

        assertTrue(updatedVehicle.isPresent());
        assertEquals("NEW123", updatedVehicle.get().getVehicleNumber());
    }

    @Test
    void delete() {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        session.save(user);

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleNumber("ABC123");
        vehicle.setTrailerNumber("XYZ987");
        vehicle.setDriverName("John Doe");
        vehicle.setInfo("Contact: 123-456-7890");

        vehicleDao.save(vehicle);

        vehicleDao.delete(vehicle.getId());

        Optional<Vehicle> deletedVehicle = vehicleDao.findById(vehicle.getId(), Collections.emptyMap());

        assertFalse(deletedVehicle.isPresent());
    }

    @Test
    void findAll() {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        session.save(user);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setUser(user);
        vehicle1.setVehicleNumber("ABC123");
        vehicleDao.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setUser(user);
        vehicle2.setVehicleNumber("XYZ987");
        vehicleDao.save(vehicle2);

        List<Vehicle> vehicles = vehicleDao.findAll();

        assertEquals(2, vehicles.size());
    }

    @Test
    void findByVehicleNumber() {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        session.save(user);

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleNumber("ABC123");
        vehicleDao.save(vehicle);

        Optional<Vehicle> foundVehicle = vehicleDao.findByVehicleNumber("ABC123");

        assertTrue(foundVehicle.isPresent());
        assertEquals("ABC123", foundVehicle.get().getVehicleNumber());
    }
}
