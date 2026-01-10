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
        assertEquals(0, manager.remainingTaskCount());
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
}