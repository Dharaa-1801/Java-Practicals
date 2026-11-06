package com.example.taskmanager.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents one task in the application.
 */
public final class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String title;
    private boolean completed;

    public Task(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be blank");
        }
        this.title = title.trim();
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + title;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Task task)) {
            return false;
        }
        return completed == task.completed && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, completed);
    }
}
