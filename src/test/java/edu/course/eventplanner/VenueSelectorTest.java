package edu.course.eventplanner;

import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.VenueSelector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VenueSelectorTest {

    @Test
    void testSelectVenueWithinBudget() {
        List<Venue> venues = List.of(
                new Venue("Cheap", 1000, 50, 5, 10),
                new Venue("Fancy", 5000, 200, 20, 10)
        );
        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(1500, 40);
        assertEquals("Cheap", selected.getName());
    }

    @Test
    void testSelectNoValidVenue() {
        List<Venue> venues = List.of(
                new Venue("Expensive", 9000, 500, 50, 10)
        );
        VenueSelector selector = new VenueSelector(venues);
        assertNull(selector.selectVenue(1000, 50));
    }

    @Test
    void testSelectCheapestValidVenue() {
        List<Venue> venues = List.of(
                new Venue("A", 2000, 100, 10, 10),
                new Venue("B", 1500, 100, 10, 10),
                new Venue("C", 3000, 100, 10, 10)
        );
        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(5000, 80);
        assertEquals("B", selected.getName());
    }

    @Test
    void testTieBreakerByCapacity() {
        List<Venue> venues = List.of(
                new Venue("A", 2000, 80, 10, 10),
                new Venue("B", 2000, 60, 10, 10)
        );
        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(3000, 50);
        assertEquals("B", selected.getName());
    }
}