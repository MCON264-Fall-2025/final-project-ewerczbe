package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    void undoRestoresTaskToUpcoming() {
        TaskManager tm = new TaskManager();
        Task t = new Task("Setup");

        tm.addTask(t);
        tm.executeNextTask();

        Task undone = tm.undoLastTask();

        assertEquals(t, undone);
        assertEquals(1, tm.remainingTaskCount());
    }


    @Test
    void undoReturnsNullWhenNoCompletedTasks() {
        TaskManager tm = new TaskManager();
        assertNull(tm.undoLastTask());
    }

    @Test
    void executeMovesTaskToCompleted() {
        TaskManager tm = new TaskManager();
        Task t = new Task("Decorate");

        tm.addTask(t);
        Task executed = tm.executeNextTask();

        assertEquals(t, executed);
        assertEquals(0, tm.remainingTaskCount());
    }
}