package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void addGuest_increasesGuestCount() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "friends"));

        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void addGuest_allowsDuplicateNames() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "family"));
        manager.addGuest(new Guest("Bob", "family"));

        assertEquals(2, manager.getGuestCount());
    }

    @Test
    void findGuest_byId_returnsGuest() {
        GuestListManager manager = new GuestListManager();
        Guest g = new Guest("Carol", "neighbors");
        manager.addGuest(g);

        Guest found = manager.findGuest(g.getId());
        assertNotNull(found);
        assertEquals("Carol", found.getName());
    }

    @Test
    void removeGuest_existingGuest_returnsTrue() {
        GuestListManager manager = new GuestListManager();
        Guest g = new Guest("Dave", "friends");
        manager.addGuest(g);

        boolean removed = manager.removeGuest(g.getId());
        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void removeGuest_missingGuest_returnsFalse() {
        GuestListManager manager = new GuestListManager();
        assertFalse(manager.removeGuest(java.util.UUID.randomUUID()));
    }

    @Test
    void addGuest_doesNothingWhenGuestIsNull() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(null);
        assertEquals(0, manager.getGuestCount());
    }
}