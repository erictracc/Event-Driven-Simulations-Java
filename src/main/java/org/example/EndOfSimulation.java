package org.example;

/**
 * EndOfSimulation: the class representing the final event that stops the
 * simulation.
 */
class EndOfSimulation extends Event {

    /**
     * Constructor.
     * @param time double
     */
    public EndOfSimulation(double time) {
        super(time);
    }

    /**
     * makeItHappen: end of simulation event routine.
     */
    public void makeItHappen() {
        Sim.stats.snapshot();
    }
}