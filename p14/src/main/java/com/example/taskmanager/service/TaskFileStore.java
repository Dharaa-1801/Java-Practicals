package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Persists task data using Java object serialization.
 */
public final class TaskFileStore {
    public void save(TaskService service, Path file) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(file))) {
            output.writeObject(service.getTasks());
        }
    }

    public TaskService load(Path file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(file))) {
            @SuppressWarnings("unchecked")
            List<Task> savedTasks = (List<Task>) input.readObject();
            TaskService service = new TaskService();
            for (Task task : savedTasks) {
                service.addTask(task.getTitle());
                if (task.isCompleted()) {
                    service.completeTask(service.count());
                }
            }
            return service;
        }
    }
}
