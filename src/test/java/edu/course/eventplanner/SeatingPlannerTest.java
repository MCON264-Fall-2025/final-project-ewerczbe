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
                new Guest("A", "Group1"),
                new Guest("B", "Group1"),
                new Guest("C", "Group2"),
                new Guest("D", "Group2")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(2, seating.size());
        assertEquals(2, seating.get(1).size());
        assertEquals(2, seating.get(2).size());

        assertEquals("A", seating.get(1).get(0).getName());
        assertEquals("B", seating.get(1).get(1).getName());
        assertEquals("C", seating.get(2).get(0).getName());
        assertEquals("D", seating.get(2).get(1).getName());
    }
}