package org.example;

import java.util.*;
import java.io.*;

/** 
 * CSC 270 simulation example
 * Adapted January 1998 by J. Clarke from a c++ version, itself based on a
 * Turing original by M. Molle
 *
 * Sim: the class in charge of the simulation.
 * This class contains both the main() method for the application, and the
 * "global" variables controlling the execution.
 */

public class Sim {
    // "Global" quantities used throughout the simulation
    public static double simulationTime; // What time is it?
    public static double reportInterval; // How often should we report?

    // quantities that determine how we model the real world
    // In a more elaborate program, these might be input data.

    // economics: profit per litre of gas, and cost to operate one pump for a day
    public static double profit = 0.025;
    public static double pumpCost = 20;

    // demand; minimum and maximum amount of gas needed by a car
    // See Car constructor.
    public static double litresNeededMin = 10;
    public static double litresNeededRange = 50;

    // service times: constant base time + time per litre + random spread
    // See Pump.serviceTime().
    public static double serviceTimeBase = 150;
    public static double serviceTimePerLitre = 0.5;
    public static double serviceTimeSpread = 30;

    // customer behaviours probability of balking depends on three
    // ad-hoc constants. See Arrival.doesCarBalk().
    public static double balkA = 40;
    public static double balkB = 25;
    public static double balkC = 3;

    // customer arrival rate
    // See Arrival.interarrivalTime().
    public static double meanInterarrivalTime = 50; // seconds

    // random-number streams used to model the world
    public static Random arrivalStream; // auto arrival times
    public static Random litreStream; // number of litres needed
    public static Random balkingStream; // balking probability
    public static Random serviceStream; // service times

    // major data structures
    public static EventList eventList;
    public static CarQueue carQueue;
    public static PumpStand pumpStand;
    public static Statistics stats;

    /**
     * main entrypoint - starts the application
     * @param args java.lang.String[]
     */
    public static void main(java.lang.String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Read data and print introduction.
        reportInterval = Double.parseDouble(in.readLine());
        double endingTime = Double.parseDouble(in.readLine());
        int numPumps = Integer.parseInt(in.readLine());
        System.out.println("This simulation run uses " + numPumps + " pumps");

        // Initialize the random-number streams.
        System.out.println(" and the following random number seeds:");
        int seed = Integer.parseInt(in.readLine());
        arrivalStream = new Random(seed);
        System.out.println("     " + seed);
        seed = Integer.parseInt(in.readLine());
        litreStream = new Random(seed);
        System.out.println("     " + seed);
        seed = Integer.parseInt(in.readLine());
        balkingStream = new Random(seed);
        System.out.println("     " + seed);
        seed = Integer.parseInt(in.readLine());
        serviceStream = new Random(seed);
        System.out.println("     " + seed);
        System.out.println("");

        // Create and initialize the event list, the car queue, the pump stand,
        // and the statistics collector.
        eventList = new EventList();
        carQueue = new CarQueue();
        pumpStand = new PumpStand(numPumps);
        stats = new Statistics();

        // Schedule the required events:
        // the end of the simulation;
        // the first progress report;
        // the arrival of the first car.
        EndOfSimulation lastEvent = new EndOfSimulation(endingTime);
        eventList.insert(lastEvent);
        if (reportInterval <= endingTime) {
            Report nextReport = new Report(reportInterval);
            eventList.insert(nextReport);
        }
        eventList.insert(new Arrival(0));
        // (Should the first car really arrive at time 0?)

        // The "clock driver" loop
        while (true) {
            Event currentEvent = eventList.takeNextEvent();
            simulationTime = currentEvent.getTime();
            currentEvent.makeItHappen();
            if (currentEvent instanceof EndOfSimulation)
                break;
        }
    }
}