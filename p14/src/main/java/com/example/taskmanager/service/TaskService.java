package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service that owns and manages the task list.
 */
public class TaskService {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String title) {
        tasks.add(new Task(title));
    }

    public int count() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    public void completeTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException("Invalid task number: " + taskNumber);
        }
        tasks.get(taskNumber - 1).markCompleted();
    }
}
