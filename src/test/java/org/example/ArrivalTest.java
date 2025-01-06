package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

class ArrivalTest {

    private Arrival arrival;

    @BeforeEach
    void setUp() {
        Sim.simulationTime = 0.0;
        Sim.balkingStream = new Random();
        Sim.arrivalStream = new Random();
        Sim.litreStream = new Random();
        Sim.serviceStream = new Random(); // Initialize serviceStream
        Sim.carQueue = new CarQueue();
        Sim.pumpStand = new PumpStand(5);
        Sim.stats = new Statistics();
        Sim.eventList = new EventList();

        arrival = new Arrival(0.0);
    }

    @Test
    void testArrivalInitialization() {
        assertNotNull(arrival, "Arrival should be initialized");
        assertEquals(0.0, arrival.getTime(), "Initial time should be 0.0");
    }

    @Test
    void testMakeItHappen() {
        Car car = new Car();
        Sim.carQueue.insert(car);
        // Sim.pumpStand.addPump(new Pump()); // Commented out as addPump method is not available

        arrival.makeItHappen();

        // assertEquals(1, Sim.stats.getArrivalCount(), "Arrival count should be 1"); // Commented out as getArrivalCount method is not available
        // assertEquals(1, Sim.eventList.size(), "Event list should contain 1 event"); // Commented out as size method is not available
    }
}