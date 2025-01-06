package org.example;

/**
 * Arrival: the class representing arrival events.
 */
class Arrival extends Event {

    /**
     * Constructor.
     * @param time double
     */
    public Arrival(double time) {
        super(time);
    }

    /**
     * doesCarBalk: decide whether a car should balk.
     * Deciding whether to balk is an activity that forms part of the arrival
     * event, so this method belongs among the event routines.
     * The probability that a car leaves without buying gas (i.e., balks) grows
     * larger as the queue length gets larger, and grows smaller when the car
     * requires a greater number of litres of gas, so that:
     * (1) there is no balking if the queue length is zero, and
     * (2) otherwise, the probability of NOT balking is
     * (40 + litres)/(25 * (3 + queueLength))
     * @param litres double
     * @param queueLength int
     * @return boolean
     */
    private boolean doesCarBalk(double litres, int queueLength) {
        return queueLength > 0 && (Sim.balkingStream.nextDouble()
            > (Sim.balkA + litres) / (Sim.balkB * (Sim.balkC + queueLength)));
    }

    /**
     * interarrivalTime: the time until the next arrival, from an exponential
     * distribution.
     * @return double
     */
    private double interarrivalTime() {
        return -Sim.meanInterarrivalTime * Math.log(Sim.arrivalStream.nextDouble());
    }

    /**
     * makeItHappen: arrival event routine.
     */
    public void makeItHappen() {
        // Create and initialize a new auto record.
        Car arrivingCar = new Car();
        Sim.stats.countArrival();
        final double litres = arrivingCar.getLitresNeeded();
        if (doesCarBalk(litres, Sim.carQueue.getQueueSize())) {
            Sim.stats.accumBalk(litres);
        } else {
            arrivingCar.setArrivalTime(Sim.simulationTime);
            if (Sim.pumpStand.aPumpIsAvailable()) {
                Sim.pumpStand.takeAvailablePump().startService(arrivingCar);
            } else {
                Sim.carQueue.insert(arrivingCar);
            }
        }
        // Schedule the next arrival, reusing the current event object.
        setTime(Sim.simulationTime + interarrivalTime());
        Sim.eventList.insert(this);
    }
}