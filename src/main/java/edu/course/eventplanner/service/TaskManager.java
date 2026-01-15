package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TaskManager {

    private final Queue<Task> upcoming;
    private final Stack<Task> completed;

    // Tracks whether the last action was an undo
    private Task lastUndone = null;

    public TaskManager() {
        this.upcoming = new LinkedList<>();
        this.completed = new Stack<>();
    }

    public void addTask(Task task) {
        if (task != null) {
            upcoming.add(task);
        }
    }

    public Task executeNextTask() {
        if (upcoming.isEmpty()) {
            return null;
        }

        Task t = upcoming.poll();

        // Only push to completed if this is NOT the task we just undid
        if (t != lastUndone) {
            completed.push(t);
        }

        // Reset undo tracking
        lastUndone = null;

        return t;
    }

    public Task undoLastTask() {
        if (completed.isEmpty()) {
            return null;
        }

        Task undone = completed.pop();

        // Put undone task at the FRONT of the queue
        ((LinkedList<Task>) upcoming).addFirst(undone);

        // Mark this task as undone so we don't double-push it later
        lastUndone = undone;

        return undone;
    }

    public int remainingTaskCount() {
        return upcoming.size();
    }
}