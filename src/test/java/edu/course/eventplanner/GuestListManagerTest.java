package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void testAddGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "Friends"));
        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void testAddMultipleGuests() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("A", "G1"));
        manager.addGuest(new Guest("B", "G2"));
        assertEquals(2, manager.getGuestCount());
    }

    @Test
    void testRemoveGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));
        assertTrue(manager.removeGuest("Bob"));
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void testRemoveMissingGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));
        assertFalse(manager.removeGuest("NotHere"));
        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void testFindGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Charlie", "Work"));
        assertNotNull(manager.findGuest("Charlie"));
    }

    @Test
    void testFindMissingGuest() {
        GuestListManager manager = new GuestListManager();
        assertNull(manager.findGuest("Ghost"));
    }
}