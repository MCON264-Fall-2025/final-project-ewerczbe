package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import java.util.*;

public class GuestListManager {

    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestByName = new HashMap<>();

    // Add a guest to BOTH the linked list and the map
    public void addGuest(Guest guest) {
        if (guest == null) return;

        guests.add(guest);                     // linked list = source of truth
        guestByName.put(guest.getName(), guest); // fast lookup
    }

    // Remove a guest by name
    public boolean removeGuest(String guestName) {
        Guest g = guestByName.remove(guestName);
        if (g == null) {
            return false; // not found
        }

        // Remove from linked list
        return guests.remove(g);
    }

    // Find a guest by name in O(1)
    public Guest findGuest(String guestName) {
        return guestByName.get(guestName);
    }

    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return guests;
    }
}