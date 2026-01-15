package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;

import java.util.*;

public class GuestListManager {

    // Linked list is the master source of truth
    private final LinkedList<Guest> guests;
    // Map for fast lookup by name
    private final Map<String, Guest> guestByName;

    public GuestListManager() {
        this.guests = new LinkedList<>();
        this.guestByName = new HashMap<>();
    }

    public void addGuest(Guest guest) {
        if (guest == null) {
            return;
        }
        guests.add(guest);
        // If multiple guests share a name, we keep the most recently added
        guestByName.put(guest.getName(), guest);
    }

    public boolean removeGuest(String guestName) {
        if (guestName == null) {
            return false;
        }
        Guest found = guestByName.get(guestName);
        if (found == null) {
            return false;
        }
        boolean removed = guests.remove(found);
        if (removed) {
            // Remove from map only if this exact guest was removed
            guestByName.remove(guestName);
        }
        return removed;
    }

    public Guest findGuest(String guestName) {
        if (guestName == null) {
            return null;
        }
        return guestByName.get(guestName);
    }

    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }
}