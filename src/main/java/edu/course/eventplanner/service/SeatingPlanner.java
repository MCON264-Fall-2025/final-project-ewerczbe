package edu.course.eventplanner.service;

import edu.course.eventplanner.model.*;
import java.util.*;

public class SeatingPlanner {
    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {

        Map<String, Queue<Guest>> groups = new HashMap<>();
        for (Guest g : guests) {
            groups.computeIfAbsent(g.getGroupTag(), k -> new LinkedList<>())
                    .add(g);
        }

        TreeSet<Table> tables = new TreeSet<>();
        for (int i = 1; i <= venue.getTables(); i++) {
            tables.add(new Table(i, venue.getSeatsPerTable()));
        }

        for (String tag : groups.keySet()) {
            Queue<Guest> q = groups.get(tag);

            for (Table table : tables) {
                while (!q.isEmpty() && table.hasSpace()) {
                    table.seatGuest(q.poll());
                }
                if (q.isEmpty()) break;
            }
        }

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