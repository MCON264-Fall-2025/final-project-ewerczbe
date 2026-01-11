package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void testAddGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "Friends"));
        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void testAddNullGuestDoesNothing() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(null);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void testAddMultipleGuests() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("A", "G1"));
        manager.addGuest(new Guest("B", "G2"));
        manager.addGuest(new Guest("C", "G3"));
        assertEquals(3, manager.getGuestCount());
    }

    @Test
    void testRemoveGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));
        assertTrue(manager.removeGuest("Bob"));
    }

    @Test
    void testRemoveMissingGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));
        assertFalse(manager.removeGuest("NotHere"));
    }

    @Test
    void testRemoveGuestTwice() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));
        manager.removeGuest("Bob");
        assertFalse(manager.removeGuest("Bob"));
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

    @Test
    void testGetAllGuestsReturnsList() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("A", "G1"));
        List<Guest> list = manager.getAllGuests();
        assertEquals(1, list.size());
    }

    @Test
    void testGetAllGuestsReflectsChanges() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("A", "G1"));
        manager.addGuest(new Guest("B", "G2"));
        assertEquals(2, manager.getAllGuests().size());
    }
}