package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void testAddTask() {
        TaskManager tm = new TaskManager();
        tm.addTask(new Task("Decorate"));

        assertEquals(1, tm.remainingTaskCount());
    }

    @Test
    void testExecuteNextTask() {
        TaskManager tm = new TaskManager();
        tm.addTask(new Task("Setup"));
        tm.addTask(new Task("Cook"));

        Task executed = tm.executeNextTask();

        assertNotNull(executed);
        assertEquals("Setup", executed.getDescription());
        assertEquals(1, tm.remainingTaskCount());
    }

    @Test
    void testExecuteNextTask_empty() {
        TaskManager tm = new TaskManager();

        Task executed = tm.executeNextTask();

        assertNull(executed);
        assertEquals(0, tm.remainingTaskCount());
    }


    @Test
    void testUndoLastTask_empty() {
        TaskManager tm = new TaskManager();

        Task undone = tm.undoLastTask();

        assertNull(undone);
    }
}