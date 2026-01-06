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
                new Venue("Cheap Hall", 1000, 50, 5, 10),
                new Venue("Fancy Hall", 5000, 200, 20, 10)
        );

        VenueSelector selector = new VenueSelector(venues);

        Venue selected = selector.selectVenue(1500, 40);

        assertNotNull(selected);
        assertEquals("Cheap Hall", selected.getName());
    }
}