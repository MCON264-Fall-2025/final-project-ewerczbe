package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    void addTask_increasesRemainingTaskCount() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Decorate"));

        assertEquals(1, manager.remainingTaskCount());
    }

    @Test
    void executeNextTask_returnsTaskAndRemovesFromQueue() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Set up chairs");

        manager.addTask(task);
        Task executed = manager.executeNextTask();

        assertEquals(task, executed);
        assertEquals(0, manager.remainingTaskCount());
    }

    @Test
    void undoLastTask_returnsLastCompletedTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Prepare food");

        manager.addTask(task);
        manager.executeNextTask();

        Task undone = manager.undoLastTask();
        assertEquals(task, undone);
    }

    @Test
    void undoLastTask_whenNoneExecuted_returnsNull() {
        TaskManager manager = new TaskManager();

        assertNull(manager.undoLastTask());
    }

    @Test
    void undoLastTask_returnsNullWhenNothingCompleted() {
        TaskManager manager = new TaskManager();

        Task undone = manager.undoLastTask();

        assertNull(undone);
    }

    @Test
    void remainingTaskCount_decreasesAfterExecution() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Decorate"));
        manager.addTask(new Task("Setup chairs"));

        manager.executeNextTask();

        assertEquals(1, manager.remainingTaskCount());
    }

    @Test
    void executeNextTask_returnsNullWhenNoTasksExist() {
        TaskManager manager = new TaskManager();

        Task task = manager.executeNextTask();

        assertNull(task);
    }

    @Test
    void undoAfterExecute_restoresLastTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Decorate");

        manager.addTask(task);
        manager.executeNextTask();

        Task undone = manager.undoLastTask();

        assertEquals(task, undone);
    }
}
