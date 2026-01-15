package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import java.util.*;

public class VenueSelector {

    private final Set<Venue> venues;

    public VenueSelector(List<Venue> venueList) {
        this.venues = new TreeSet<>(
                Comparator.comparingDouble(Venue::getCost)
                        .thenComparingInt(Venue::getCapacity)
                        .thenComparing(Venue::getName)
        );
        this.venues.addAll(venueList);
    }

    public List<Venue> getAllVenues() {
        return new ArrayList<>(venues);
    }

    public Venue selectVenue(double maxCost, int minCapacity) {
        for (Venue v : venues) {
            if (v.getCost() <= maxCost && v.getCapacity() >= minCapacity) {
                return v;
            }
        }
        return null;
    }
}