package com.springshortpath.app;

import com.springshortpath.app.model.City;
import com.springshortpath.app.model.Route;
import com.springshortpath.app.repository.CityRepository;
import com.springshortpath.app.repository.RouteRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppApplicationTests {

    static Neo4jContainer<?> container = new Neo4jContainer<>("neo4j:4.3-community");

    @Autowired
    Driver driver;

    @Autowired
    CityRepository cityRepository;
    @Autowired
    RouteRepository routeRepository;
    private final UUID cityId = UUID.randomUUID();
    private final UUID route1Id = UUID.randomUUID();
    private final UUID route2Id = UUID.randomUUID();

    @BeforeAll
    static void startContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> container.getAdminPassword());
        registry.add("spring.neo4j.uri", () -> container.getBoltUrl());
    }

    @BeforeEach
    void createCityWithRoute() {
        City city = new City("Braunschweig");
        city.setId(cityId);

        Route route1 = new Route();
        route1.setId(route1Id);
        route1.setDuration(1111d);

        Set<Route> routes = new HashSet<>();
        routes.add(route1);
        city.setRoutes(routes);
        cityRepository.save(city);
    }

    @Test
    void number1() {
        List<City> cities = cityRepository.listAll();
        City loadedCity = cities.get(0);
        assertThat(loadedCity.getName()).isNotBlank();
        assertThat(loadedCity.getRoutes()).hasSize(1);

        Route route2 = new Route();
        route2.setId(route2Id);
        route2.setDuration(2222d);
        loadedCity.getRoutes().add(route2);
        cityRepository.save(loadedCity);

        cities = cityRepository.listAll();
        loadedCity = cities.get(0);
        assertThat(loadedCity.getName()).isNotBlank();
        assertThat(loadedCity.getRoutes()).hasSize(2);
    }

    @Test
    void number2() {
        City city = cityRepository.getById(cityId);
        assertThat(city).isNotNull();
        assertThat(city.getName()).isNotBlank();
    }

    @Test
    void number3() {
        List<Route> routes = routeRepository.listAllByCityId(cityId);

        assertThat(routes).hasSize(1);
    }

    @Test
    void number4() {
        routeRepository.deleteRoute(cityId, route1Id);
    }

    @AfterAll
    static void tearDownContainer() {
        container.stop();
    }

}
