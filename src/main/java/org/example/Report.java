package org.example;

/**
 * Report: the class representing reporting events.
 */
class Report extends Event {

    /**
     * Constructor.
     * @param time double
     */
    public Report(double time) {
        super(time);
    }

    /**
     * makeItHappen: interim reporting event routine.
     */
    public void makeItHappen() {
        Sim.stats.snapshot();

        // Schedule the next interim report.
        setTime(Sim.simulationTime + Sim.reportInterval);
        Sim.eventList.insert(this);
    }
}