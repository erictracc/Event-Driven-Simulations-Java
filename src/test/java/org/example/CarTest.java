package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class CarTest {

    @BeforeEach
    void setUp() {
        Sim.litreStream = new Random(); // Initialize litreStream before each test
    }

    @Test
    void testCarInitialization() {
        Car car = new Car();
        assertNotNull(car, "Car should be initialized");
        assertTrue(car.getLitresNeeded() >= 10 && car.getLitresNeeded() <= 60, "Litres needed should be between 10 and 60");
    }

    @Test
    void testGetAndSetArrivalTime() {
        Car car = new Car();
        car.setArrivalTime(15.0);
        assertEquals(15.0, car.getArrivalTime(), "Arrival time should be set correctly");
    }
}