package org.example;

/**
 * Car: the class representing cars.
 */
class Car {

    private double arrivalTime;
    private double litresNeeded;

    /**
     * Constructor.
     *
     * The number of litres required is a property of a car, so it belongs in
     * this class. It is also something the car "knows" when it arrives, so it
     * should be calculated in the constructor.
     *
     * The distribution of litres required is uniform between 10 and 60.
     */
    public Car() {
        litresNeeded = Sim.litresNeededMin
            + Sim.litreStream.nextDouble() * Sim.litresNeededRange;
    }

    /**
     * getArrivalTime: return the car's arrival time.
     * @return double
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * getLitresNeeded: return the number of litres of fuel needed by the car.
     * @return double
     */
    public double getLitresNeeded() {
        return litresNeeded;
    }

    /**
     * setArrivalTime: set the car's arrival time.
     * @param time double
     */
    public void setArrivalTime(double time) {
        arrivalTime = time;
    }
}