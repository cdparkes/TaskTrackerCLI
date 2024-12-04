package cli;

import handlers.StatusHandler;
import handlers.Task;
import repository.TaskService;

// CLI class provides command-line interaction for task management
public class CLI {
    private final TaskService taskService; // Service for task operations

    public CLI(TaskService taskService) {
        this.taskService = taskService;
    }

    // Method to print the selection menu to the console
    public static void printHelp() {
        System.out.println("""
                Select what you want to do:
                '(A)dd' a task to your task list
                '(U)pdate' a task in your task list
                '(D)elete' a task in your task list
                'mark-in-progress' to mark a task as in progress
                'mark-done' to mark a task as done
                '(L)ist /done /todo /in-progress to list all or categories
                '(E)xit""");

    }

    // Method to handle user input and execute commands
    public void start(String[] input) {
        if (input.length == 0 || input[0].isBlank()) {
            System.out.println("No command provided. Use 'help' for a list of commands.");
            return;
        }


        try {
            switch (input[0].toLowerCase()) {
                case "a", "add" -> addTask(String.join(" ", java.util.Arrays.copyOfRange(input, 1, input.length)));
                case "u", "update" -> updateTask(Long.parseLong(input[1]), String.join(" ", java.util.Arrays.copyOfRange(input, 2, input.length)));
                case "d", "delete" -> deleteTask(Long.parseLong(input[1])); // Delete a task
                case "mark-in-progress" -> markTask(Long.parseLong(input[1]), StatusHandler.IN_PROGRESS); // Mark task as in-progress
                case "mark-done" -> markTask(Long.parseLong(input[1]), StatusHandler.DONE); // Mark task as done
                case "l", "list" -> listTasksOrByStatus(input);
                case "help", "?", "h" -> printHelp();
                default -> System.out.println("Unknown  command. Use 'help' for a list of commands.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please ensure IDs are numeric.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient command arguments provided. Use 'help' for more information.");
        }
    }

    private void listTasksOrByStatus(String[] input) {
        if (input.length == 1) {
            listTasks();
        } else {
            switch (input[1]) {
                case "done" -> listTaskByStatus(StatusHandler.DONE);
                case "todo" -> listTaskByStatus(StatusHandler.TODO);
                case "in-progress" -> listTaskByStatus(StatusHandler.IN_PROGRESS);
                default -> System.out.println("Unknown list filter. Use 'list', 'list done', 'list todo' or 'list in-progress'.");
            }
        }
    }

    // Add a new task with the given description
    private void addTask(String description) {
        Long success = taskService.addTask(new Task(description));
        if (success != null) {
            System.out.println("Task added successfully (ID: " + success + ").");
        }
    }

    // Update a task description by its ID
    private void updateTask(Long id, String description) {
        boolean success = taskService.updateTask(id, description);
        if (success) {
            System.out.println("Task ID: " + id + " successfully updated.");
        }
    }

    // Delete a task by its ID
    private void deleteTask(Long id) {
        boolean success = taskService.deleteTask(id);
        if (success) {
            System.out.println("Task ID: " + id + " successfully deleted");
        }
    }

    // Mark a task with a given status
    private void markTask(Long id, StatusHandler statusHandler) {
        boolean success = taskService.markTask(id, statusHandler);
        if (success) {
            System.out.println("Task ID: " + id + " successfully marked as " + statusHandler + ".");
        }
    }

    // Print all tasks to the console
    private void listTasks() {
        System.out.println(taskService.findAll());
    }

    // Print tasks filtered by status to the console
    private void listTaskByStatus(StatusHandler statusHandler) {
        System.out.println(taskService.findAllByStatus(statusHandler));
    }
}
