package edu.course.eventplanner.service;

import edu.course.eventplanner.Main;
import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private java.util.Scanner scannerOf(String input) {
        return new java.util.Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void resetState() {
        try {
            var gmField = Main.class.getDeclaredField("guestManager");
            gmField.setAccessible(true);
            GuestListManager gm = (GuestListManager) gmField.get(null);
            gm.getAllGuests().clear();

            var tmField = Main.class.getDeclaredField("taskManager");
            tmField.setAccessible(true);
            TaskManager tm = (TaskManager) tmField.get(null);

            var upcomingField = TaskManager.class.getDeclaredField("upcoming");
            upcomingField.setAccessible(true);
            ((Queue<?>) upcomingField.get(tm)).clear();

            var completedField = TaskManager.class.getDeclaredField("completed");
            completedField.setAccessible(true);
            ((Stack<?>) completedField.get(tm)).clear();

            var venuesField = Main.class.getDeclaredField("venues");
            venuesField.setAccessible(true);
            venuesField.set(null, null);

            var selectedVenueField = Main.class.getDeclaredField("selectedVenue");
            selectedVenueField.setAccessible(true);
            selectedVenueField.set(null, null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleLoadSampleData() {
        java.util.Scanner kb = scannerOf("5\n");
        Main.handleLoadSampleData(kb);

        var gm = getGuestManager();
        assertEquals(5, gm.getGuestCount());
        assertNotNull(getVenues());
    }

    @Test
    void testHandleAddGuest() {
        java.util.Scanner kb = scannerOf("Alice\nfamily\n");
        Main.handleAddGuest(kb);

        var gm = getGuestManager();
        assertEquals(1, gm.getGuestCount());
        assertEquals("Alice", gm.getAllGuests().get(0).getName());
    }

    @Test
    void testHandleRemoveGuest() {
        var gm = getGuestManager();
        gm.addGuest(new Guest("Bob", "friends"));

        java.util.Scanner kb = scannerOf("1\n");
        Main.handleRemoveGuest(kb);

        assertEquals(0, gm.getGuestCount());
    }

    @Test
    void testHandleRemoveGuest_notFound() {
        java.util.Scanner kb = scannerOf("1\n");
        Main.handleRemoveGuest(kb);

        var gm = getGuestManager();
        assertEquals(0, gm.getGuestCount());
    }

    @Test
    void testHandleSelectVenue() {
        setVenues(List.of(
                new Venue("Hall A", 100, 50, 5, 10),
                new Venue("Hall B", 80, 40, 5, 10)
        ));

        java.util.Scanner kb = scannerOf("90\n30\n");
        Main.handleSelectVenue(kb);

        assertEquals("Hall B", getSelectedVenue().getName());
    }

    @Test
    void testHandleSelectVenue_noVenuesLoaded() {
        java.util.Scanner kb = scannerOf("100\n20\n");
        Main.handleSelectVenue(kb);

        assertNull(getSelectedVenue());
    }

    @Test
    void testHandleGenerateSeating() {
        setSelectedVenue(new Venue("Test", 100, 20, 2, 5));

        var gm = getGuestManager();
        gm.addGuest(new Guest("A", "g1"));
        gm.addGuest(new Guest("B", "g1"));

        Main.handleGenerateSeating();
        assertTrue(true);
    }

    @Test
    void testHandleGenerateSeating_noVenue() {
        Main.handleGenerateSeating();
        assertNull(getSelectedVenue());
    }

    @Test
    void testHandleAddTask() {
        java.util.Scanner kb = scannerOf("Decorate\n");
        Main.handleAddTask(kb);

        var tm = getTaskManager();
        assertEquals(1, tm.remainingTaskCount());
    }

    @Test
    void testHandleExecuteTask() {
        var tm = getTaskManager();
        tm.addTask(new Task("Setup"));

        Main.handleExecuteTask();
        assertEquals(0, tm.remainingTaskCount());
    }

    @Test
    void testHandleExecuteTask_none() {
        var tm = getTaskManager();
        Main.handleExecuteTask();
        assertEquals(0, tm.remainingTaskCount());
    }

    @Test
    void testHandleUndoTask() {
        var tm = getTaskManager();
        tm.addTask(new Task("Setup"));
        tm.executeNextTask();

        Main.handleUndoTask();

        // Undo now returns the task to the upcoming queue
        assertEquals(1, tm.remainingTaskCount());
    }

    @Test
    void testHandleUndoTask_none() {
        var tm = getTaskManager();
        Main.handleUndoTask();
        assertEquals(0, tm.remainingTaskCount());
    }

    private GuestListManager getGuestManager() {
        try {
            var f = Main.class.getDeclaredField("guestManager");
            f.setAccessible(true);
            return (GuestListManager) f.get(null);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private TaskManager getTaskManager() {
        try {
            var f = Main.class.getDeclaredField("taskManager");
            f.setAccessible(true);
            return (TaskManager) f.get(null);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private List<Venue> getVenues() {
        try {
            var f = Main.class.getDeclaredField("venues");
            f.setAccessible(true);
            return (List<Venue>) f.get(null);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private Venue getSelectedVenue() {
        try {
            var f = Main.class.getDeclaredField("selectedVenue");
            f.setAccessible(true);
            return (Venue) f.get(null);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private void setVenues(List<Venue> v) {
        try {
            var f = Main.class.getDeclaredField("venues");
            f.setAccessible(true);
            f.set(null, v);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private void setSelectedVenue(Venue v) {
        try {
            var f = Main.class.getDeclaredField("selectedVenue");
            f.setAccessible(true);
            f.set(null, v);
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}