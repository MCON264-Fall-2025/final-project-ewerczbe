package edu.course.eventplanner.model;

public class Table implements Comparable<Table> {

    private final int tableNumber;
    private int remainingSeats;

    public Table(int tableNumber, int remainingSeats) {
        this.tableNumber = tableNumber;
        this.remainingSeats = remainingSeats;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void seatOne() {
        remainingSeats--;
    }

    @Override
    public int compareTo(Table other) {
        return Integer.compare(this.tableNumber, other.tableNumber);
    }
}