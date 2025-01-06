package org.example;

/**
 * Departure: the class representing departure events.
 */
class Departure extends Event {
    private Pump pump;

    /**
     * Constructor.
     * @param time double
     */
    public Departure(double time) {
        super(time);
    }

    /**
     * makeItHappen: departure event routine.
     */
    public void makeItHappen() {
        // Precondition: pump != null && pump.getCarInService() != null
        // Identify the departing car and collect statistics.
        Car departingCar = pump.getCarInService();
        Sim.stats.accumSale(departingCar.getLitresNeeded());
        // The car vanishes and the pump is free; can we serve another car?
        if (Sim.carQueue.getQueueSize() > 0) {
            pump.startService(Sim.carQueue.takeFirstCar());
        } else {
            Sim.pumpStand.releasePump(pump);
        }
    }

    /**
     * setPump: assign a pump to this departure.
     * @param pump Pump
     */
    public void setPump(Pump pump) {
        this.pump = pump;
    }
}