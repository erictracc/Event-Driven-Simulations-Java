package org.example;

/**
 * Pump: the class representing single pumps at the gas station
 */
class Pump {
    private Car carInService;

    /**
     * getCarInService: return the car currently being served by the pump.
     * @return Car
     */
    public Car getCarInService() {
        return carInService;
    }

    /**
     * serviceTime: determine how long the service will take.
     * This is a property of the pump-car combination, so the method could have
     * been in the Car class if the appropriate information were available there.
     * 
     * Service times have a normal distribution with a mean given by a constant
     * base plus an amount of time per litre, and with a fixed standard 
     * deviation.
     * @return double
     */
    private double serviceTime() {
        if (carInService == null) {
            System.out.println("Error: no car in service when expected");
            return -1.0;
        }
        return Sim.serviceTimeBase 
            + Sim.serviceTimePerLitre * carInService.getLitresNeeded()
            + Sim.serviceTimeSpread * Sim.serviceStream.nextGaussian();
    }

    /**
     * startService: the start-of-service event routine.
     * Connects the car to this pump, and determines when the service will stop.
     * @param car Car
     */
    public void startService(Car car) {
        // Precondition: Sim.pumpStand.aPumpIsAvailable()
        // Match the auto to an available pump.
        carInService = car;
        final double pumpTime = serviceTime();
        // Collect statistics.
        Sim.stats.accumWaitingTime(Sim.simulationTime - carInService.getArrivalTime());
        Sim.stats.accumServiceTime(pumpTime);
        // Schedule departure of car from this pump.
        Departure dep = new Departure(Sim.simulationTime + pumpTime);
        dep.setPump(this);
        Sim.eventList.insert(dep);
    }
}