package repository;

import handlers.FileHandler;
import handlers.Task;
import mappers.TaskMapper;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

// TaskDataAccess manages interaction between in-memory tasks and file storage
public class TaskDataAccess {
    private final FileHandler fileHandler; // Handles file operations
    private final SortedSet<Task> tasks; // Stores tasks in a sorted set
    private final TaskMapper taskMapper; // Maps tasks to/from JSON

    // Constructor initializes the data access with file handler and loads tasks
    public TaskDataAccess(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.taskMapper = new TaskMapper();
        this.tasks = new TreeSet<>(findAll()); // Load all tasks into a sorted set
    }

    // Return the sorted set of tasks
    public SortedSet<Task> getTasks() {
        return tasks;
    }

    // Finds and returns all tasks from the file
    private List<Task> findAll() {
        List<String> lines = fileHandler.readAllLines(); // Read task data from file
        // Convert JSON data to Task objects
        return taskMapper.jsonToTasks(lines);
    }

    // Saves all tasks to file in JSON format
    public void saveAll() {
        if (tasks.isEmpty()) {
            fileHandler.writeJsonToFile(""); // Write empty content if no tasks
        } else {
            fileHandler.writeJsonToFile(taskMapper.tasksToJson(tasks)); // Convert tasks to JSON and save
        }
    }
}
