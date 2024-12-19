package org.example;

/**
 * EventList: the class for the event list.
 * (There is only one object of this class in the program.)
 */
class EventList {
    /**
     * ListItem: the class for objects stored in the event list.
     */
    private class ListItem {
        // The event list is a linked list, so each item contains a data field
        // and a "next item" field. This is just a simple record structure, so
        // we'll allow outsiders to access the fields directly instead of using
        // get and set methods.
        public Event data;
        public ListItem next;
    }

    private ListItem firstEvent;

    /**
     * Constructor.
     */
    public EventList() {
        firstEvent = null; // happens automatically, but done explicitly
        // here to clarify the "empty list" state.
    }

    /**
     * insert: add an event e to the event list in the appropriate place,
     * prioritized by time.
     * @param e Event
     */
    public void insert(Event e) {
        // Create the item to go on the event list.
        ListItem item = new ListItem();
        item.data = e;
        // Find the appropriate place for the item in the event list,
        // and put it there.
        final double time = e.getTime();
        if (firstEvent == null || time < firstEvent.data.getTime()) {
            item.next = firstEvent;
            firstEvent = item;
        } else {
            ListItem behind = firstEvent;
            ListItem ahead = firstEvent.next;
            while (ahead != null && ahead.data.getTime() <= time) {
                behind = ahead;
                ahead = ahead.next;
            }
            behind.next = item;
            item.next = ahead;
        }
    }

    /**
     * takeNextEvent: remove the item at the head of the event list and
     * return it.
     * @return Event
     */
    public Event takeNextEvent() {
        // Precondition: firstEvent != null
        if (firstEvent == null) {
            System.out.println("Error! ran out of events");
            return null;
        }
        Event eventToReturn = firstEvent.data;
        firstEvent = firstEvent.next;
        return eventToReturn;
    }
}