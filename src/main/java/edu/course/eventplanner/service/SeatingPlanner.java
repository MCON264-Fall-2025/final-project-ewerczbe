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

        // Test expectation: if no guests → return empty map
        if (guests == null || guests.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, Queue<Guest>> groups = new HashMap<>();
        for (Guest g : guests) {
            groups.computeIfAbsent(g.getGroupTag(), k -> new LinkedList<>()).add(g);
        }

        // Test expectation: if venue has 0 tables → create 1 default table
        int tableCount = Math.max(1, venue.getTables());

        TreeSet<Table> tables = new TreeSet<>();
        for (int i = 1; i <= tableCount; i++) {
            tables.add(new Table(i, venue.getSeatsPerTable()));
        }

        // Seat guests group by group
        for (String tag : groups.keySet()) {
            Queue<Guest> q = groups.get(tag);

            for (Table table : tables) {
                while (!q.isEmpty() && table.hasSpace()) {
                    table.seatGuest(q.poll());
                }
                if (q.isEmpty()) break;
            }

            // If still guests left (overflow), seat them at table 1
            while (!q.isEmpty()) {
                tables.first().seatGuest(q.poll());
            }
        }

        // Build result map
        Map<Integer, List<Guest>> result = new HashMap<>();
        for (Table t : tables) {
            result.put(t.tableNumber, t.seated);
        }

        return result;
    }

    private static class Table implements Comparable<Table> {
        private final int tableNumber;
        private final int capacity;
        private final List<Guest> seated = new ArrayList<>();

        Table(int tableNumber, int capacity) {
            this.tableNumber = tableNumber;
            this.capacity = capacity;
        }

        boolean hasSpace() {
            return seated.size() < capacity;
        }

        void seatGuest(Guest g) {
            seated.add(g);
        }

        @Override
        public int compareTo(Table other) {
            return Integer.compare(this.tableNumber, other.tableNumber);
        }
    }
}