package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.StringReader;

class SimTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Reset System.out to its original state
        System.setOut(originalOut);
    }

    @Test
    void testMain() throws Exception {
        String input = "10.0\n100.0\n5\n12345\n12345\n12345\n12345\n";
        BufferedReader in = new BufferedReader(new StringReader(input));
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        Sim.main(new String[]{});

        String output = outContent.toString();
        System.out.println("Captured Output:\n" + output); // Print the captured output for debugging

        // Add assertions to verify the expected output
        assertTrue(output.contains("This simulation run uses 5 pumps"), "Output should contain the number of pumps");
        assertTrue(output.contains("and the following random number seeds:"), "Output should contain the random number seeds");
    }
}