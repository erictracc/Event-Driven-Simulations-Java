package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

class EndOfSimulationTest {

    private EndOfSimulation endOfSimulation;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        Sim.simulationTime = 0.0;
        Sim.balkingStream = new Random();
        Sim.arrivalStream = new Random();
        Sim.litreStream = new Random();
        Sim.serviceStream = new Random();
        Sim.carQueue = new CarQueue();
        Sim.pumpStand = new PumpStand(5);
        Sim.stats = new Statistics();
        Sim.eventList = new EventList();

        endOfSimulation = new EndOfSimulation(100.0); // Assuming the simulation ends at time 100.0

        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Reset System.out to its original state
        System.setOut(originalOut);
    }

    @Test
    void testEndOfSimulationInitialization() {
        assertNotNull(endOfSimulation, "EndOfSimulation should be initialized");
        assertEquals(100.0, endOfSimulation.getTime(), "End time should be 100.0");
    }
}