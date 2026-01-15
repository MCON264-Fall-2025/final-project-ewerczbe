package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;

import java.util.*;

public class VenueSelector {

    // BST (TreeSet) sorted by cost, then capacity, then name
    private final Set<Venue> sortedVenues;

    public VenueSelector(List<Venue> venues) {
        this.sortedVenues = new TreeSet<>(
                Comparator.comparingDouble(Venue::getCost)
                        .thenComparingInt(Venue::getCapacity)
                        .thenComparing(Venue::getName)
        );
        if (venues != null) {
            this.sortedVenues.addAll(venues);
        }
    }

    public Venue selectVenue(double budget, int guestCount) {
        for (Venue v : sortedVenues) {
            if (v.getCost() <= budget && v.getCapacity() >= guestCount) {
                return v;
            }
        }
        return null;
    }
}