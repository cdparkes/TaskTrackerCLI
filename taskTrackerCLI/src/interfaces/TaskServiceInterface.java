package interfaces;

import handlers.StatusHandler;
import handlers.Task;

import java.util.List;

public interface TaskServiceInterface {

    List<Task> findAll();

    List<Task> findAllByStatus(StatusHandler statusHandler);

    Long addTask(Task task);

    boolean updateTask(Long id, String description);

    boolean deleteTask(Long id);

    boolean markTask(Long id, StatusHandler statusHandler);
}
