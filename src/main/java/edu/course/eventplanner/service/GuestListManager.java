package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import java.util.*;

public class GuestListManager {

    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<UUID, Guest> guestById = new HashMap<>();

    public void addGuest(Guest guest) {
        if (guest == null) return;

        guests.add(guest);
        guestById.put(guest.getId(), guest);
    }

    public boolean removeGuest(UUID guestId) {
        Guest g = guestById.remove(guestId);
        if (g == null) {
            return false;
        }
        return guests.remove(g);
    }

    public Guest findGuest(UUID guestId) {
        return guestById.get(guestId);
    }

    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return guests;
    }
}