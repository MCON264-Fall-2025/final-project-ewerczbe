package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VenueSelectorTest {

    @Test
    void testSelectVenueWithinBudget() {
        List<Venue> venues = List.of(
                new Venue("A", 100, 50, 5, 10),
                new Venue("B", 80, 40, 4, 10),
                new Venue("C", 120, 100, 10, 10)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue result = selector.selectVenue(90, 30);

        assertNotNull(result);
        assertEquals("B", result.getName());
    }

    @Test
    void testSelectVenueCapacityTooSmall() {
        List<Venue> venues = List.of(
                new Venue("A", 100, 20, 2, 10),
                new Venue("B", 80, 15, 2, 10)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue result = selector.selectVenue(200, 50);

        assertNull(result);
    }

    @Test
    void testSelectVenueNoValidOption() {
        List<Venue> venues = List.of(
                new Venue("A", 300, 200, 20, 10),
                new Venue("B", 250, 150, 15, 10)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue result = selector.selectVenue(100, 50);

        assertNull(result);
    }

    @Test
    void testSelectCheapestValidVenue() {
        List<Venue> venues = List.of(
                new Venue("A", 100, 100, 10, 10),
                new Venue("B", 90, 100, 10, 10),
                new Venue("C", 95, 100, 10, 10)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue result = selector.selectVenue(200, 80);

        assertNotNull(result);
        assertEquals("B", result.getName());
    }
}