package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.SeatingPlanner;
import edu.course.eventplanner.service.TaskManager;
import edu.course.eventplanner.service.VenueSelector;
import edu.course.eventplanner.util.Generators;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final GuestListManager guestManager = new GuestListManager();
    private static final TaskManager taskManager = new TaskManager();
    private static List<Venue> venues = null;
    private static Venue selectedVenue = null;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        printMenu();
        int choice = kb.nextInt();
        kb.nextLine();

        while (choice != 0) {
            switch (choice) {
                case 1 -> handleLoadSampleData(kb);
                case 2 -> handleAddGuest(kb);
                case 3 -> handleRemoveGuest(kb);
                case 4 -> handleSelectVenue(kb);
                case 5 -> handleGenerateSeating();
                case 6 -> handleAddTask(kb);
                case 7 -> handleExecuteTask();
                case 8 -> handleUndoTask();
                case 9 -> handlePrintSummary();
            }

            printMenu();
            choice = kb.nextInt();
            kb.nextLine();
        }
    }

    public static void handleLoadSampleData(Scanner kb) {
        System.out.print("How many guests do you want to generate? ");
        int count = kb.nextInt();
        kb.nextLine();

        venues = Generators.generateVenues();
        List<Guest> generated = Generators.GenerateGuests(count);

        for (Guest g : generated) {
            guestManager.addGuest(g);
        }
    }

    public static void handleAddGuest(Scanner kb) {
        String name = kb.nextLine();
        String tag = kb.nextLine();
        guestManager.addGuest(new Guest(name, tag));
    }

    public static void handleRemoveGuest(Scanner kb) {
        List<Guest> all = guestManager.getAllGuests();
        if (all.isEmpty()) return;

        int index = kb.nextInt();
        kb.nextLine();

        if (index < 1 || index > all.size()) return;

        Guest g = all.get(index - 1);
        guestManager.removeGuest(g.getName());
    }

    public static void handleSelectVenue(Scanner kb) {
        if (venues == null || venues.isEmpty()) {
            selectedVenue = null;
            return;
        }

        double budget = kb.nextDouble();
        int guests = kb.nextInt();
        kb.nextLine();

        VenueSelector selector = new VenueSelector(venues);
        selectedVenue = selector.selectVenue(budget, guests);
    }

    public static void handleGenerateSeating() {
        if (selectedVenue == null) return;

        SeatingPlanner planner = new SeatingPlanner(selectedVenue);
        Map<Integer, List<Guest>> seating =
                planner.generateSeating(guestManager.getAllGuests());
    }

    public static void handleAddTask(Scanner kb) {
        String desc = kb.nextLine();
        taskManager.addTask(new Task(desc));
    }

    public static void handleExecuteTask() {
        taskManager.executeNextTask();
    }

    public static void handleUndoTask() {
        taskManager.undoLastTask();
    }

    public static void handlePrintSummary() {
        guestManager.getGuestCount();
        if (selectedVenue != null) selectedVenue.getName();
        taskManager.remainingTaskCount();
    }

    public static void printMenu() {
        System.out.println("1. Load sample data");
        System.out.println("2. Add guest");
        System.out.println("3. Remove guest");
        System.out.println("4. Select venue");
        System.out.println("5. Generate seating chart");
        System.out.println("6. Add preparation task");
        System.out.println("7. Execute next task");
        System.out.println("8. Undo last task");
        System.out.println("9. Print event summary");
        System.out.println("0. Exit");
    }
}