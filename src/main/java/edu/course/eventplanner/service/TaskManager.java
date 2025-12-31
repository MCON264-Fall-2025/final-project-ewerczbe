package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;

public class TaskManager {

    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();


    public void addTask(Task task) {
        if (task != null) {
            upcoming.add(task);
        }
    }
    public Task executeNextTask() {
        if (upcoming.isEmpty()) {
            return null;
        }

        Task next = upcoming.poll();
        completed.push(next);
        return next;
    }

    public Task undoLastTask() {
        if (completed.isEmpty()) {
            return null;
        }

        return completed.pop();
    }

    public int remainingTaskCount() {
        return upcoming.size();
    }
}