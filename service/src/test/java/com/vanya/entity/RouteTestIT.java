package com.vanya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class RouteTestIT {
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
    public void whenSaveRouteThenRouteIsSaved() {
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

        session.persist(user);
        session.persist(route);
        session.getTransaction().commit();
        session.clear();

        Route foundRoute = session.find(Route.class, route.getId());
        assertThat(foundRoute).isNotNull();
        assertThat(foundRoute.getStartPoint()).isEqualTo("Start Point");
        assertThat(foundRoute.getUser().getUsername()).startsWith("unique_username_");
    }

    @Test
    public void whenFindRouteByIdThenRouteIsReturned() {
        User user = User.builder()
                .username("unique_username_" + System.currentTimeMillis())
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
        session.getTransaction().commit();
        session.clear();

        Route foundRoute = session.find(Route.class, route.getId());

        assertThat(foundRoute).isNotNull();
        assertThat(foundRoute.getEndPoint()).isEqualTo("End Point");
    }

    @Test
    public void whenUpdateRouteThenRouteIsUpdated() {
        User user = User.builder()
                .username("unique_username_" + System.currentTimeMillis())
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
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        Route foundRoute = session.find(Route.class, route.getId());
        foundRoute.setPrice(200.0);
        session.merge(foundRoute);
        session.getTransaction().commit();
        session.clear();

        Route updatedRoute = session.find(Route.class, route.getId());
        assertThat(updatedRoute.getPrice()).isEqualTo(200.0);
    }

    @Test
    public void whenDeleteRouteThenRouteIsDeleted() {
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
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        Route foundRoute = session.find(Route.class, route.getId());
        session.remove(foundRoute);
        session.getTransaction().commit();
        session.clear();

        Route deletedRoute = session.find(Route.class, route.getId());
        assertThat(deletedRoute).isNull();
    }
}
