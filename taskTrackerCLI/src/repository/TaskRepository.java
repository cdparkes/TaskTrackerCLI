package repository;

import handlers.FileHandler;
import handlers.StatusHandler;
import handlers.Task;
import interfaces.TaskRepositoryInterface;

import java.util.List;

// TaskRepository connects business logic to data access, implement the repository interface
public class TaskRepository implements TaskRepositoryInterface {
    private final TaskDataAccess taskDataAccess; // Manages task persistence

    // Constructor initializes TaskDataAccess with a FileHandler
    public TaskRepository(FileHandler fileHandler) {
        taskDataAccess = new TaskDataAccess(fileHandler);
    }

    // Getter to access TaskDataAccess object
    public TaskDataAccess getTaskDataAccess() {
        return taskDataAccess;
    }

    // Retrieves all tasks as a list
    @Override
    public List<Task> findAll() {
        return taskDataAccess.getTasks().stream().toList(); // Convert SortedSet to List
    }

    // Retrieves tasks filtered by specified status
    @Override
    public List<Task> findAllByStatus(StatusHandler taskStatus) {
        return taskDataAccess.getTasks().stream()
                .filter(task -> task.getStatus().equals(taskStatus)) // Filter tasks by status
                .toList(); // Convert filtered results to List
    }

    // Saves all tasks to file via TaskDataAccess
    @Override
    public void saveAll() {
        taskDataAccess.saveAll(); // Delegate saving functionality
    }
}
