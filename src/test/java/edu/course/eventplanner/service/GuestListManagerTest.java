package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestListManagerTest {

    @Test
    void testAddGuest() {
        GuestListManager gm = new GuestListManager();
        gm.addGuest(new Guest("Alice", "family"));

        assertEquals(1, gm.getGuestCount());
        assertEquals("Alice", gm.getAllGuests().get(0).getName());
    }

    @Test
    void testRemoveGuest() {
        GuestListManager gm = new GuestListManager();
        gm.addGuest(new Guest("Bob", "friends"));

        assertTrue(gm.removeGuest("Bob"));
        assertEquals(0, gm.getGuestCount());
    }

    @Test
    void testRemoveGuest_notFound() {
        GuestListManager gm = new GuestListManager();
        gm.addGuest(new Guest("Bob", "friends"));

        assertFalse(gm.removeGuest("Alice"));
        assertEquals(1, gm.getGuestCount());
    }

    @Test
    void testFindGuest() {
        GuestListManager gm = new GuestListManager();
        gm.addGuest(new Guest("Charlie", "neighbors"));

        Guest found = gm.findGuest("Charlie");
        assertNotNull(found);
        assertEquals("neighbors", found.getGroupTag());
    }

    @Test
    void testFindGuest_notFound() {
        GuestListManager gm = new GuestListManager();
        gm.addGuest(new Guest("Dana", "coworkers"));

        assertNull(gm.findGuest("Eve"));
    }
}