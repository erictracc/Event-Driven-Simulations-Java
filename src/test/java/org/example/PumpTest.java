package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class PumpTest {

    @BeforeEach
    void setUp() {
        Sim.serviceStream = new Random(); // Initialize serviceStream before each test
        Sim.litreStream = new Random(); // Initialize litreStream before each test
        Sim.stats = new Statistics(); // Initialize stats before each test
        Sim.eventList = new EventList(); // Initialize eventList before each test
    }

    @Test
    void testPumpInitialization() {
        Pump pump = new Pump();
        assertNotNull(pump, "Pump should be initialized");
    }

    @Test
    void testGetCarInService() {
        Pump pump = new Pump();
        assertNull(pump.getCarInService(), "No car should be in service initially");
    }

    @Test
    void testStartService() {
        Pump pump = new Pump();
        Car car = new Car();
        car.setArrivalTime(10.0); // Set the arrival time
        pump.startService(car);
        assertEquals(car, pump.getCarInService(), "Car should be in service");
    }
}