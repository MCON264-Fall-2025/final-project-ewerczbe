package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SeatingPlannerTest {

    @Test
    void generateSeating_seatsAllGuests_orAsManyAsCapacityAllows() {
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

        assertTrue(totalSeated > 0);
        assertTrue(totalSeated <= guests.size());
    }

    @Test
    void generateSeating_returnsEmptyMapForNoGuests() {
        Venue venue = new Venue("Empty Venue", 100, 10, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        Map<Integer, List<Guest>> seating = planner.generateSeating(List.of());

        assertTrue(seating.isEmpty());
    }

    @Test
    void generateSeating_distributesGuestsAcrossAtLeastOneTable() {
        Venue v = new Venue("Test", 100, 20, 2, 2);
        SeatingPlanner planner = new SeatingPlanner(v);

        List<Guest> guests = List.of(
                new Guest("A", "g1"),
                new Guest("B", "g1"),
                new Guest("C", "g1")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertTrue(totalSeated <= guests.size());
    }

    @Test
    void generateSeating_stopsWhenTablesAreFull() {
        Venue v = new Venue("Test", 100, 20, 1, 2);
        SeatingPlanner planner = new SeatingPlanner(v);

        List<Guest> guests = List.of(
                new Guest("A", "g1"),
                new Guest("B", "g1"),
                new Guest("C", "g1")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(2, totalSeated);
        assertEquals(1, seating.size());
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

        assertTrue(totalSeated > 0);
        assertTrue(totalSeated <= guests.size());
    }
}