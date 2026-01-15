package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SeatingPlannerTest {

    @Test
    void testSeatingGroupsStayTogether() {
        Venue v = new Venue("TestVenue", 100, 50, 5, 4);
        SeatingPlanner planner = new SeatingPlanner(v);

        List<Guest> guests = List.of(
                new Guest("A1", "family"),
                new Guest("A2", "family"),
                new Guest("B1", "friends"),
                new Guest("B2", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(1, seating.size());
        assertEquals(4, seating.get(1).size());
    }

    @Test
    void testSeatingRespectsTableSize() {
        Venue v = new Venue("TestVenue", 100, 50, 5, 2);
        SeatingPlanner planner = new SeatingPlanner(v);

        List<Guest> guests = List.of(
                new Guest("A1", "family"),
                new Guest("A2", "family"),
                new Guest("A3", "family")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(2, seating.size());
        assertEquals(2, seating.get(1).size());
        assertEquals(1, seating.get(2).size());
    }

    @Test
    void testEmptyGuestList() {
        Venue v = new Venue("TestVenue", 100, 50, 5, 4);
        SeatingPlanner planner = new SeatingPlanner(v);

        Map<Integer, List<Guest>> seating = planner.generateSeating(List.of());

        assertTrue(seating.isEmpty());
    }
}