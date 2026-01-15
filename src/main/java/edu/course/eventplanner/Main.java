package edu.course.eventplanner;

import edu.course.eventplanner.model.*;
import edu.course.eventplanner.service.*;
import edu.course.eventplanner.util.Generators;

import java.util.*;

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
                default -> System.out.println("Invalid option.");
            }

            printMenu();
            choice = kb.nextInt();
            kb.nextLine();
        }

        System.out.println("Goodbye!");
    }

    public static void handleLoadSampleData(Scanner kb) {
        System.out.print("How many guests do you want to generate? ");
        int gCount = kb.nextInt();
        kb.nextLine();

        venues = Generators.generateVenues();
        List<Guest> autoGuests = Generators.GenerateGuests(gCount);

        for (Guest g : autoGuests) {
            guestManager.addGuest(g);
        }

        System.out.println("Sample venues and guests loaded.");
    }

    public static void handleAddGuest(Scanner kb) {
        System.out.print("Enter guest name: ");
        String name = kb.nextLine();
        System.out.print("Enter group tag: ");
        String tag = kb.nextLine();

        Guest g = new Guest(name, tag);
        guestManager.addGuest(g);

        System.out.println("Guest added with ID: " + g.getId());
    }

    public static void handleRemoveGuest(Scanner kb) {
        List<Guest> all = guestManager.getAllGuests();

        if (all.isEmpty()) {
            System.out.println("No guests to remove.");
            return;
        }

        System.out.println("Guests:");
        for (int i = 0; i < all.size(); i++) {
            Guest g = all.get(i);
            System.out.println((i + 1) + ". " + g.getName() + " (" + g.getGroupTag() + ") - ID: " + g.getId());
        }

        System.out.print("Select guest number to remove: ");
        int index = kb.nextInt();
        kb.nextLine();

        if (index < 1 || index > all.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Guest g = all.get(index - 1);
        boolean removed = guestManager.removeGuest(g.getId());

        if (removed) {
            System.out.println("Guest removed.");
        } else {
            System.out.println("Could not remove guest.");
        }
    }

    public static void handleSelectVenue(Scanner kb) {
        if (venues == null || venues.isEmpty()) {
            System.out.println("No venues available.");
            selectedVenue = null;
            return;
        }

        System.out.print("Enter max cost: ");
        double maxCost = kb.nextDouble();
        System.out.print("Enter min capacity: ");
        int minCap = kb.nextInt();
        kb.nextLine();

        VenueSelector selector = new VenueSelector(venues);
        selectedVenue = selector.selectVenue(maxCost, minCap);

        if (selectedVenue == null) {
            System.out.println("No suitable venue found.");
        } else {
            System.out.println("Selected venue: " + selectedVenue.getName());
        }
    }

    public static void handleGenerateSeating() {
        if (selectedVenue == null) {
            System.out.println("Select a venue first.");
            return;
        }

        SeatingPlanner planner = new SeatingPlanner(selectedVenue);
        Map<Integer, List<Guest>> seating =
                planner.generateSeating(guestManager.getAllGuests());

        System.out.println("=== Seating Chart ===");
        for (int table : seating.keySet()) {
            System.out.println("Table " + table + ":");
            for (Guest g : seating.get(table)) {
                System.out.println("  - " + g.getName() + " (" + g.getGroupTag() + ")");
            }
        }
    }

    public static void handleAddTask(Scanner kb) {
        System.out.print("Enter task description: ");
        String desc = kb.nextLine();

        taskManager.addTask(new Task(desc));
        System.out.println("Task added.");
    }

    public static void handleExecuteTask() {
        Task next = taskManager.executeNextTask();

        if (next == null) {
            System.out.println("No tasks to execute.");
        } else {
            System.out.println("Executed: " + next.getDescription());
        }
    }

    public static void handleUndoTask() {
        Task undone = taskManager.undoLastTask();
        if (undone == null) {
            System.out.println("No completed tasks to undo.");
        } else {
            System.out.println("Undone: " + undone.getDescription());
        }
    }

    public static void handlePrintSummary() {
        System.out.println("\n=== Event Summary ===");
        System.out.println("Guests: " + guestManager.getGuestCount());

        if (selectedVenue != null) {
            System.out.println("Venue: " + selectedVenue.getName());
        } else {
            System.out.println("Venue: Not selected");
        }

        System.out.println("Remaining tasks: " + taskManager.remainingTaskCount());
    }

    public static void printMenu() {
        System.out.println("\n=== Event Planner Menu ===");
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
        System.out.print("Choose an option: ");
    }
}