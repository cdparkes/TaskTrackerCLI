package repository;

import handlers.StatusHandler;
import handlers.Task;
import interfaces.TaskServiceInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// TaskService provides business logic for managing tasks
public class TaskService implements TaskServiceInterface {
    private final TaskRepository taskRepository; // Repository to access task data

    // Constructor initializes TaskService with a TaskRepository
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Retrieve all tasks through the repository
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    // Retrieve tasks filtered by status
    @Override
    public List<Task> findAllByStatus(StatusHandler statusHandler) {
        return taskRepository.findAllByStatus(statusHandler);
    }

    // Add a new task, assigning a unique ID, and save it
    @Override
    public Long addTask(Task task) {
        if (taskRepository.getTaskDataAccess().getTasks().isEmpty()) {
            task.setId(1L); // Start with ID 1 if no tasks exist
        } else {
            Long last_id = taskRepository.getTaskDataAccess().getTasks().last().getId();
            task.setId(last_id + 1); // Increment ID for new task
        }
        taskRepository.getTaskDataAccess().getTasks().add(task); // Add task to the set
        taskRepository.saveAll(); // Persist changes
        return taskRepository.getTaskDataAccess().getTasks().last().getId();
    }

    // Update the description of a specific task identified by ID
    @Override
    public boolean updateTask(Long id, String description) {
        for (Task task : taskRepository.getTaskDataAccess().getTasks()) {
            if (task.getId().equals(id)) {
                task.setDescription(description); // Update task description
                taskRepository.saveAll(); // Persist updated task
                return true; // Exit after finding and updating the task
            }
        }
        return false;
    }

    // Delete a task based on its ID
    @Override
    public boolean deleteTask(Long id) {
        // Remove task if the ID matches, then save changes
        taskRepository.getTaskDataAccess().getTasks().removeIf(task -> task.getId().equals(id));
        taskRepository.saveAll();
        return true;
    }

    // Change the status of a task and update its last-modified timestamp
    @Override
    public boolean markTask(Long id, StatusHandler statusHandler) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        for (Task task : taskRepository.getTaskDataAccess().getTasks()) {
            if (task.getId().equals(id)) {
                task.setStatus(statusHandler); // Update status
                String formattedDateTime = LocalDateTime.now().format(formatter);
                task.setUpdatedAt(LocalDateTime.parse(formattedDateTime, formatter));
                // Set updated timestamp
                taskRepository.saveAll(); // Persist status change
                return true; // Exit after updating and saving
            }
        }
        return false;
    }
}
