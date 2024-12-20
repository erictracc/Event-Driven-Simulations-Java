package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ReportTest {

    private Report report;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        report = new Report(0.0); // Initialize Report with a starting time

        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Reset System.out to its original state
        System.setOut(originalOut);
    }

    @Test
    void testReportInitialization() {
        assertNotNull(report, "Report should be initialized");
        assertEquals(0.0, report.getTime(), "Initial time should be 0.0");
    }
}