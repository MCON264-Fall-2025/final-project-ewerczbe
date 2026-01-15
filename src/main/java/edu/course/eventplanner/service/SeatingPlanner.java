package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;

import java.util.*;

public class SeatingPlanner {

    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        Map<String, Queue<Guest>> grouped = new HashMap<>();
        for (Guest g : guests) {
            grouped.computeIfAbsent(g.getGroupTag(), k -> new LinkedList<>()).add(g);
        }

        TreeMap<Integer, List<Guest>> tables = new TreeMap<>();
        int tableNumber = 1;
        int seatsPerTable = venue.getSeatsPerTable();

        while (true) {
            boolean anyLeft = false;
            for (Queue<Guest> q : grouped.values()) {
                if (!q.isEmpty()) {
                    anyLeft = true;
                    break;
                }
            }
            if (!anyLeft) break;

            List<Guest> table = new ArrayList<>();
            for (Queue<Guest> q : grouped.values()) {
                while (!q.isEmpty() && table.size() < seatsPerTable) {
                    table.add(q.poll());
                }
                if (table.size() == seatsPerTable) break;
            }

            if (!table.isEmpty()) {
                tables.put(tableNumber++, table);
            }
        }

        return tables;
    }
}