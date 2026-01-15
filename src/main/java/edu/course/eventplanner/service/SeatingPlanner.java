package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Table;
import edu.course.eventplanner.model.Venue;

import java.util.*;

public class SeatingPlanner {

    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        int tableCount = venue.getTableCount();
        int seatsPerTable = venue.getSeatsPerTable();

        PriorityQueue<Table> tables = new PriorityQueue<>();
        for (int i = 1; i <= tableCount; i++) {
            tables.add(new Table(i, seatsPerTable));
        }

        Map<Integer, List<Guest>> seating = new TreeMap<>();

        for (Guest g : guests) {
            Table t = tables.poll();
            if (t == null || t.getRemainingSeats() == 0) {
                break;
            }

            seating.computeIfAbsent(t.getTableNumber(), k -> new ArrayList<>()).add(g);
            t.seatOne();
            tables.add(t);
        }

        return seating;
    }
}