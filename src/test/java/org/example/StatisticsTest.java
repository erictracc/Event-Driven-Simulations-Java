package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

class StatisticsTest {

    private Statistics stats;

    @BeforeEach
    void setUp() {
        stats = new Statistics();
    }

    @AfterEach
    void tearDown() {
        stats = null;
    }

    @Test
    void testInitialization() {
        assertNotNull(stats, "Statistics should be initialized");
    }

    @Test
    void testAccumBalk() throws NoSuchFieldException, IllegalAccessException {
        stats.accumBalk(10.0);
        assertEquals(1, getFieldValue(stats, "balkingCustomers"), "Balking customers should be updated");
        assertEquals(10.0, (double) getFieldValue(stats, "totalLitresMissed"), 0.001, "Total litres missed should be updated");
    }

    @Test
    void testAccumSale() throws NoSuchFieldException, IllegalAccessException {
        stats.accumSale(20.0);
        assertEquals(1, getFieldValue(stats, "customersServed"), "Customers served should be updated");
        assertEquals(20.0, (double) getFieldValue(stats, "totalLitresSold"), 0.001, "Total litres sold should be updated");
    }

    @Test
    void testAccumServiceTime() throws NoSuchFieldException, IllegalAccessException {
        stats.accumServiceTime(30.0);
        assertEquals(30.0, (double) getFieldValue(stats, "totalServiceTime"), 0.001, "Total service time should be updated");
    }

    @Test
    void testAccumWaitingTime() throws NoSuchFieldException, IllegalAccessException {
        stats.accumWaitingTime(40.0);
        assertEquals(40.0, (double) getFieldValue(stats, "totalWaitingTime"), 0.001, "Total waiting time should be updated");
    }

    @Test
    void testCountArrival() throws NoSuchFieldException, IllegalAccessException {
        stats.countArrival();
        assertEquals(1, getFieldValue(stats, "totalArrivals"), "Total arrivals should be updated");
    }
/**
    @Test
    void testSnapshot() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        stats.snapshot();

        String output = outContent.toString();
        System.setOut(originalOut);
        System.out.println("Captured Output:\n" + output);

        assertTrue(output.contains("Statistics Snapshot"), "Output should contain 'Statistics Snapshot'");
    }
*/
    private Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}