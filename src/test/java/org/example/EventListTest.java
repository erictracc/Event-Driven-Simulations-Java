package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventListTest {

    private EventList eventList;

    @BeforeEach
    void setUp() {
        eventList = new EventList();
    }

    // Concrete subclass of Event for testing purposes
    private static class TestEvent extends Event {
        public TestEvent(double time) {
            super(time);
        }

        @Override
        public void makeItHappen() {
            // Implement the abstract method for testing purposes
        }
    }

    @Test
    void testInsertAndTakeNextEvent() {
        // Create some events
        Event event1 = new TestEvent(10.0);
        Event event2 = new TestEvent(5.0);
        Event event3 = new TestEvent(15.0);

        // Insert events into the event list
        eventList.insert(event1);
        eventList.insert(event2);
        eventList.insert(event3);

        // Take events from the event list and verify the order
        assertEquals(event2, eventList.takeNextEvent(), "First event should be event2");
        assertEquals(event1, eventList.takeNextEvent(), "Second event should be event1");
        assertEquals(event3, eventList.takeNextEvent(), "Third event should be event3");
    }

    @Test
    void testTakeNextEventFromEmptyList() {
        // Attempt to take an event from an empty list
        assertNull(eventList.takeNextEvent(), "Taking an event from an empty list should return null");
    }
}