package edu.course.eventplanner;

import edu.course.eventplanner.model.*;
import edu.course.eventplanner.service.*;
import edu.course.eventplanner.util.Generators;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GuestListManager guestManager = new GuestListManager();
        TaskManager taskManager = new TaskManager();
        List<Venue> venues = null;
        Venue selectedVenue = null;

        while (true) {
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

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("How many guests do you want to generate? ");
                    int gCount = sc.nextInt();
                    sc.nextLine();

                    venues = Generators.generateVenues();


                    List<Guest> autoGuests = Generators.GenerateGuests(gCount);
                    for (Guest g : autoGuests) {
                        guestManager.addGuest(g); // required rule: must use addGuest()
                    }

                    System.out.println("Sample venues and guests loaded.");
                    break;

                case 2:
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter group tag: ");
                    String tag = sc.nextLine();
                    guestManager.addGuest(new Guest(name, tag));
                    System.out.println("Guest added.");
                    break;

                case 3:
                    System.out.print("Enter guest name to remove: ");
                    String removeName = sc.nextLine();
                    if (guestManager.removeGuest(removeName)) {
                        System.out.println("Guest removed.");
                    } else {
                        System.out.println("Guest not found.");
                    }
                    break;

                case 4:
                    if (venues == null) {
                        System.out.println("Load sample data first.");
                        break;
                    }
                    System.out.print("Enter budget: ");
                    double budget = sc.nextDouble();
                    System.out.print("Enter guest count: ");
                    int count = sc.nextInt();
                    VenueSelector selector = new VenueSelector(venues);
                    selectedVenue = selector.selectVenue(budget, count);
                    if (selectedVenue == null) {
                        System.out.println("No venue fits your requirements.");
                    } else {
                        System.out.println("Selected venue: " + selectedVenue.getName());
                    }
                    break;

                case 5:
                    if (selectedVenue == null) {
                        System.out.println("Select a venue first.");
                        break;
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
                    break;

                case 6: // Add preparation task
                    System.out.print("Enter task description: ");
                    String desc = sc.nextLine();
                    taskManager.addTask(new Task(desc));
                    System.out.println("Task added.");
                    break;

                case 7: // Execute next task
                    Task next = taskManager.executeNextTask();
                    if (next == null) {
                        System.out.println("No tasks to execute.");
                    } else {
                        System.out.println("Executed: " + next.getDescription());
                    }
                    break;

                case 8: // Undo last task
                    Task undone = taskManager.undoLastTask();
                    if (undone == null) {
                        System.out.println("No tasks to undo.");
                    } else {
                        System.out.println("Undone: " + undone.getDescription());
                    }
                    break;

                case 9: // Print event summary
                    System.out.println("\n=== Event Summary ===");
                    System.out.println("Guests: " + guestManager.getGuestCount());
                    if (selectedVenue != null) {
                        System.out.println("Venue: " + selectedVenue.getName());
                    } else {
                        System.out.println("Venue: Not selected");
                    }
                    System.out.println("Remaining tasks: " + taskManager.remainingTaskCount());
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}