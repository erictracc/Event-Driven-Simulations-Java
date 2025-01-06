package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

class DepartureTest {

    private Statistics stats;
    private Departure departure;
    private Pump pump;

    @BeforeEach
    void setUp() {
        stats = new Statistics();
        departure = new Departure(15.0);
        pump = new Pump(); // Create a new Pump instance
        Car car = new Car(); // Create a new Car instance
        pump.startService(car); // Set the car in service at the pump
        departure.setPump(pump); // Assign the pump to the departure
    }

    @AfterEach
    void tearDown() {
        stats = null;
        departure = null;
        pump = null;
    }

    /**
    @Test
    void testDeparture() throws NoSuchFieldException, IllegalAccessException {
        // Assuming makeItHappen method processes the departure
        departure.makeItHappen();

        // Verify the fields updated by the makeItHappen method
        assertEquals(1, getFieldValue(stats, "customersServed"), "Customers served should be updated");
        assertEquals(15.0, (double) getFieldValue(stats, "totalLitresSold"), 0.001, "Total litres sold should be updated");
    }

    private Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
    */
}