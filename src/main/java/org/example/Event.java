package org.example;

/**
 * Event: the class representing events within the simulation model.
 * Remember that events are not entities in the same sense as cars and pumps
 * are, and the event queue does not have the same reality as the car queue.
 * The event queue is a data structure without a real-world equivalent, while
 * the car queue is real and you can see it. Events are not quite so imaginary,
 * but they are certainly less visible than cars.
 */
abstract class Event {
    private double time; // the time when the event happens

    /**
     * Constructor.
     * @param time double
     */
    public Event(double time) {
        this.time = time;
    }

    /**
     * getTime: return the time of the event.
     * @return double
     */
    public double getTime() {
        return time;
    }

    /**
     * makeItHappen: the event routine.
     */
    public abstract void makeItHappen();

    /**
     * setTime: set the time of the event.
     * @param time double
     */
    public void setTime(double time) {
        this.time = time;
    }
}