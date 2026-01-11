package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VenueSelectorTest {

    @Test
    void selectVenue_returnsCheapestValidVenue() {
        List<Venue> venues = List.of(
                new Venue("Small Hall", 100.0, 30, 4, 8),
                new Venue("Medium Hall", 80.0, 30, 5, 6),
                new Venue("Large Hall", 80.0, 100, 12, 8)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(90.0, 25);

        assertNotNull(selected);
        assertEquals("Medium Hall", selected.getName());
    }

    @Test
    void selectVenue_returnsNull_whenNoVenueFits() {
        List<Venue> venues = List.of(
                new Venue("Tiny Room", 50.0, 5, 1, 5)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(40.0, 10);

        assertNull(selected);
    }

    @Test
    void selectVenue_prefersLowerCapacityWhenCostsTie() {
        List<Venue> venues = List.of(
                new Venue("Big Hall", 100, 200, 20, 10),
                new Venue("Smaller Hall", 100, 100, 10, 10)
        );

        VenueSelector selector = new VenueSelector(venues);
        Venue selected = selector.selectVenue(150, 80);

        assertNotNull(selected);
        assertEquals("Smaller Hall", selected.getName());
    }

    @Test
    void selectVenue_returnsNullWhenVenueListEmpty() {
        VenueSelector selector = new VenueSelector(List.of());

        Venue selected = selector.selectVenue(100, 10);

        assertNull(selected);
    }
}