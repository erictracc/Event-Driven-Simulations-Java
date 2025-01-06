package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

class CarQueueTest {

    private CarQueue carQueue;

    @BeforeEach
    void setUp() {
        carQueue = new CarQueue(); // Initialize carQueue before each test
        Sim.litreStream = new Random(); // Initialize Sim.litreStream before each test
    }

    @Test
    void testCarQueueInitialization() {
        assertNotNull(carQueue, "CarQueue should be initialized");
        assertEquals(0, carQueue.getQueueSize(), "Queue size should be 0 initially");
        assertEquals(0, carQueue.getEmptyTime(), "Empty time should be 0 initially");
    }

    @Test
    void testInsertCar() {
        Car car = new Car();
        carQueue.insert(car);
        assertEquals(1, carQueue.getQueueSize(), "Queue size should be 1 after inserting a car");
    }

    @Test
    void testTakeFirstCar() {
        Car car1 = new Car();
        Car car2 = new Car();
        carQueue.insert(car1);
        carQueue.insert(car2);
        assertEquals(car1, carQueue.takeFirstCar(), "First inserted car should be retrieved first");
        assertEquals(1, carQueue.getQueueSize(), "Queue size should be 1 after taking one car");
        assertEquals(car2, carQueue.takeFirstCar(), "Second inserted car should be retrieved second");
        assertEquals(0, carQueue.getQueueSize(), "Queue size should be 0 after taking all cars");
    }

    @Test
    void testTakeFirstCarEmptyQueue() {
        assertNull(carQueue.takeFirstCar(), "Taking car from empty queue should return null");
    }
}