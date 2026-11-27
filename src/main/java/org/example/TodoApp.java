package org.example;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        System.out.println("Todo CLI: add <task>, remove <index>, list, exit");
        System.out.println("Indexes start at 0. Tasks are stored only until exit.");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);
                String command = parts[0].toLowerCase(Locale.ROOT);

                switch (command) {
                    case "add" -> {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            System.out.println("Usage: add <task>");
                        } else {
                            todoList.add(parts[1]);
                            System.out.println("Added.");
                        }
                    }
                    case "remove" -> {
                        if (parts.length < 2) {
                            System.out.println("Usage: remove <index>");
                        } else {
                            try {
                                int index = Integer.parseInt(parts[1].trim());
                                System.out.println(todoList.remove(index)
                                        ? "Removed." : "Index out of range.");
                            } catch (NumberFormatException exception) {
                                System.out.println("Invalid index.");
                            }
                        }
                    }
                    case "list" -> {
                        List<String> tasks = todoList.getAll();
                        if (tasks.isEmpty()) {
                            System.out.println("(empty)");
                        }
                        for (int index = 0; index < tasks.size(); index++) {
                            System.out.printf("%d: %s%n", index, tasks.get(index));
                        }
                    }
                    case "exit" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println(
                            "Unknown command. Use: add, remove, list, exit");
                }
            }
        }
    }
}
