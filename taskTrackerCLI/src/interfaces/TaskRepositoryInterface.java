package interfaces;

import handlers.StatusHandler;
import handlers.Task;

import java.util.List;

public interface TaskRepositoryInterface {
    List<Task> findAll();

    List<Task> findAllByStatus(StatusHandler taskStatus);

    void saveAll();
}
