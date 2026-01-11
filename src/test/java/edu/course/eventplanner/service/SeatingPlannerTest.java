package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SeatingPlannerTest {

    @Test
    void generateSeating_groupsGuestsByGroupTag() {
        Venue venue = new Venue("Test Venue", 100.0, 50, 10, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("Alice", "family"),
                new Guest("Bob", "family"),
                new Guest("Carol", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertFalse(seating.isEmpty());

        int totalSeated = seating.values()
                .stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(3, totalSeated);
    }

    @Test
    void generateSeating_spreadsLargeGroupAcrossTables() {
        Venue venue = new Venue("Big Venue", 200.0, 100, 15, 8);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("G1", "friends"),
                new Guest("G2", "friends"),
                new Guest("G3", "friends"),
                new Guest("G4", "friends"),
                new Guest("G5", "friends"),
                new Guest("G6", "friends"),
                new Guest("G7", "friends"),
                new Guest("G8", "friends"),
                new Guest("G9", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertTrue(seating.size() >= 2);
    }

    @Test
    void generateSeating_seatsAllGuests() {
        Venue venue = new Venue("Test Venue", 100, 20, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "family"),
                new Guest("B", "family"),
                new Guest("C", "family"),
                new Guest("D", "family"),
                new Guest("E", "family"),
                new Guest("F", "family")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(6, totalSeated);
    }

    @Test
    void generateSeating_returnsEmptyMapForNoGuests() {
        Venue venue = new Venue("Empty Venue", 100, 10, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        Map<Integer, List<Guest>> seating = planner.generateSeating(List.of());

        assertTrue(seating.isEmpty());
    }

    @Test
    void generateSeating_withZeroTables_stillSeatsGuests() {
        Venue venue = new Venue("No Tables", 100, 10, 0, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "family"),
                new Guest("B", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertFalse(seating.isEmpty());
        assertEquals(2, seating.get(1).size());
    }

    @Test
    void generateSeating_handlesMultipleGroupTags() {
        Venue venue = new Venue("Mixed Groups", 100, 20, 4, 2);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "family"),
                new Guest("B", "friends"),
                new Guest("C", "family"),
                new Guest("D", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(4, totalSeated);
    }

}
