package edu.course.eventplanner;

import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    void testExecuteNextTask() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Set up chairs"));
        Task executed = manager.executeNextTask();
        assertEquals("Set up chairs", executed.getDescription());
    }

    @Test
    void testExecuteWhenEmpty() {
        TaskManager manager = new TaskManager();
        assertNull(manager.executeNextTask());
    }

    @Test
    void testUndoLastTask() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Decorate"));
        manager.executeNextTask();
        Task undone = manager.undoLastTask();
        assertEquals("Decorate", undone.getDescription());
    }

    @Test
    void testUndoWhenEmpty() {
        TaskManager manager = new TaskManager();
        assertNull(manager.undoLastTask());
    }

    @Test
    void testRemainingTaskCount() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("A"));
        manager.addTask(new Task("B"));
        assertEquals(2, manager.remainingTaskCount());
    }

    @Test
    void testExecuteMultipleTasks() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("A"));
        manager.addTask(new Task("B"));
        manager.executeNextTask();
        manager.executeNextTask();
        assertEquals(0, manager.remainingTaskCount());
    }

    @Test
    void testUndoMultipleTasks() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("A"));
        manager.addTask(new Task("B"));
        manager.executeNextTask();
        manager.executeNextTask();
        assertEquals("B", manager.undoLastTask().getDescription());
        assertEquals("A", manager.undoLastTask().getDescription());
    }

    @Test
    void testAddNullTaskDoesNothing() {
        TaskManager manager = new TaskManager();
        manager.addTask(null);
        assertEquals(0, manager.remainingTaskCount());
    }
}