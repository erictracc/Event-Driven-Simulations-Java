package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PumpStandTest {

    @Test
    void testPumpStandInitialization() {
        PumpStand pumpStand = new PumpStand(3);
        assertEquals(3, pumpStand.getNumberOfPumps(), "Number of pumps should be 3");
    }

    @Test
    void testPumpAvailability() {
        PumpStand pumpStand = new PumpStand(2);
        assertTrue(pumpStand.aPumpIsAvailable(), "At least one pump should be available");
    }

    @Test
    void testTakeAndReleasePump() {
        PumpStand pumpStand = new PumpStand(1);
        Pump pump = pumpStand.takeAvailablePump();
        assertNotNull(pump, "Pump should be taken");
        assertFalse(pumpStand.aPumpIsAvailable(), "No pump should be available");
        pumpStand.releasePump(pump);
        assertTrue(pumpStand.aPumpIsAvailable(), "Pump should be available again");
    }
}