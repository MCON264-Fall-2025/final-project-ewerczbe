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
        assertNotNull(executed);
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
        assertNotNull(undone);
        assertEquals("Decorate", undone.getDescription());
    }

    @Test
    void testUndoWhenEmpty() {
        TaskManager manager = new TaskManager();
        assertNull(manager.undoLastTask());
    }
}