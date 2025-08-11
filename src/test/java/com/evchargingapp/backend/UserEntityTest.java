package com.evchargingapp.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenSavingUser_thenUserIsPersisted() {
        // Given
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        // When
        User savedUser = entityManager.persistAndFlush(user);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    public void whenSavingUserWithVehicle_thenVehicleIsPersisted() {
        // Given
        User user = new User();
        user.setEmail("userwithcar@example.com");
        user.setPassword("password");

        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Tesla");
        vehicle.setModel("Model 3");
        vehicle.setConnectorType("Type 2");
        user.addVehicle(vehicle);

        // When
        User savedUser = entityManager.persistAndFlush(user);

        // Then
        assertThat(savedUser.getVehicles()).hasSize(1);
        Vehicle savedVehicle = savedUser.getVehicles().iterator().next();
        assertThat(savedVehicle.getMake()).isEqualTo("Tesla");
    }
}
