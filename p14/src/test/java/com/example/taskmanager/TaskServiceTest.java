package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskFileStore;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Unit tests for the service and its persistence behavior.

class TaskServiceTest {
    @Test
    void addingTaskIncreasesCount() {
        TaskService service = new TaskService();

        service.addTask("Read notes");
        service.addTask("Practice Java");

        assertEquals(2, service.count());
    }

    @Test
    void invalidTaskNumberThrowsException() {
        TaskService service = new TaskService();
        service.addTask("Read notes");

        assertThrows(IllegalArgumentException.class, () -> service.completeTask(2));
    }

    @Test
    void savingAndLoadingKeepsTasksUnchanged() throws Exception {
        TaskService original = new TaskService();
        original.addTask("Read notes");
        original.addTask("Practice Java");
        original.completeTask(1);

        Path file = Files.createTempFile("tasks-", ".dat");
        try {
            new TaskFileStore().save(original, file);
            TaskService loaded = new TaskFileStore().load(file);
            assertEquals(original.getTasks(), loaded.getTasks());
        } finally {
            Files.deleteIfExists(file);
        }
    }
}
