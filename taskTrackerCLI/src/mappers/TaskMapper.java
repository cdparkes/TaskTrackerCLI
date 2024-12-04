package mappers;

import handlers.StatusHandler;
import handlers.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static java.time.LocalDateTime.parse;

// Class responsible for mapping tasks to and from JSON representation
public class TaskMapper {

    // Helper method to extract a JSON property value as a string
    private String jsonPropertyValue(String jsonProperty) {
        return jsonProperty.substring(jsonProperty.indexOf(':') + 1) // Extract value after colon
                .replace("\"", "") // Remove quotes
                .replace(",", "") // Remove trailing commas
                .stripLeading(); // Remove leading spaces
    }

    // Convert a list of JSON properties to a Task object
    private Task jsonToTask(List<String> jsonProperties) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        Task task = new Task();

        try {
            // Extract and set each property of the Task
            String lineValue = jsonPropertyValue(jsonProperties.get(0));
            task.setId(Long.parseLong(lineValue));

            lineValue = jsonPropertyValue(jsonProperties.get(1));
            task.setDescription(lineValue);

            lineValue = jsonPropertyValue(jsonProperties.get(2));
            task.setStatus(StatusHandler.getEnumByLabel(lineValue));

            lineValue = jsonPropertyValue(jsonProperties.get(3));
            task.setCreatedAt(parse(lineValue, formatter));

            lineValue = jsonPropertyValue(jsonProperties.get(4));
            task.setUpdatedAt(parse(lineValue, formatter));
        } catch (Exception e) {
            return null; // Return null if parsing fails
        }

        return task;
    }

    // Convert lines of a JSON file into a list of Task objects
    public List<Task> jsonToTasks(List<String> jsonFileLines) {
        List<Task> tasks = new ArrayList<>();
        // Iterate through the lines and identify task objects by curly braces
        for (int i = 0; i < jsonFileLines.size(); i++) {
            String line = jsonFileLines.get(i).trim();
            if (line.equals("{")) {
                try {
                    Task task = jsonToTask(jsonFileLines.subList(i + 1, i + 6));
                    if (task != null) {
                        tasks.add(task); // Add task to list if created successfully
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Error extracting task properties: " + e.getMessage());
                }
            }
        }
        return tasks;
    }

    // Format a Task object into a JSON string
    public String taskFormatter(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String createdAtFormatted = task.getCreatedAt().format(formatter);
        String updatedAtFormatted = task.getUpdatedAt().format(formatter);

        return String.format("""
                        {
                        "id": "%d",
                        "description" : "%s",
                        "status" : "%s",
                        "createdAt": "%s",
                        "updatedAt": "%s"
                        }""",
                task.getId(), task.getDescription(), task.getStatus().label, createdAtFormatted, updatedAtFormatted);
    }

    // Convert a sorted set of Task objects into a JSON string
    public String tasksToJson(SortedSet<Task> tasks) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("  \"tasks\": [\n");

        int taskCount = tasks.size();
        int currentIndex = 0;

        // Append each task as a formatted JSON object
        for (Task task : tasks) {
            jsonBuilder.append(taskFormatter(task));

            // Append a comma for all but the last task
            if (currentIndex < taskCount - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }

            currentIndex++;
        }
        jsonBuilder.append("  ]\n");
        jsonBuilder.append("}\n");
        return jsonBuilder.toString(); // Return complete JSON string
    }
}
