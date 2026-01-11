package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.SeatingPlanner;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SeatingPlannerTest {

    @Test
    void testSeatingByGroup() {
        Venue venue = new Venue("Test Venue", 2000, 4, 2, 2);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "G1"),
                new Guest("B", "G1"),
                new Guest("C", "G2"),
                new Guest("D", "G2")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(2, seating.size());
        assertEquals(2, seating.get(1).size());
        assertEquals(2, seating.get(2).size());
    }

    @Test
    void testSeatingNotEnoughSpace() {
        Venue venue = new Venue("Small Venue", 1000, 2, 1, 2);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "G1"),
                new Guest("B", "G1"),
                new Guest("C", "G1")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(1, seating.size());
        assertEquals(2, seating.get(1).size());
    }
}