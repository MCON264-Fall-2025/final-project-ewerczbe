package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TaskManager {

    private final Queue<Task> upcoming;
    private final Stack<Task> completed;

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
        completed.push(t);
        return t;
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