package com.example.taskmanager;

import com.example.taskmanager.service.TaskFileStore;
import com.example.taskmanager.service.TaskService;

import java.nio.file.Path;
import java.util.Scanner;

// Console menu for the layered task manager.

public final class MenuMain {
    private static final Path DATA_FILE = Path.of("tasks.dat");

    private MenuMain() {
    }

    public static void main(String[] args) throws Exception {
        TaskService service = new TaskService();
        TaskFileStore store = new TaskFileStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add task  2. List tasks  3. Complete task");
            System.out.println("4. Save  5. Load  0. Exit");
            System.out.print("Choose an action: ");
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter task title: ");
                        service.addTask(scanner.nextLine());
                    }
                    case 2 -> service.getTasks().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("Enter task number: ");
                        service.completeTask(Integer.parseInt(scanner.nextLine()));
                    }
                    case 4 -> {
                        store.save(service, DATA_FILE);
                        System.out.println("Tasks saved.");
                    }
                    case 5 -> {
                        service = store.load(DATA_FILE);
                        System.out.println("Tasks loaded.");
                    }
                    case 0 -> {
                        System.out.println("Goodbye.");
                        return;
                    }
                    default -> throw new IllegalArgumentException("Invalid action: " + choice);
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }
}
