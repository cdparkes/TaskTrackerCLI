import cli.CLI;
import handlers.FileHandler;
import repository.TaskRepository;
import repository.TaskService;

public class TaskCLI {

    public static void main(String[] args) {
        // Define the file path for the task list JSON file
        String filePath = "C:/test/taskList.json";

        //Create a FileHandler using the file path, responsible for reading/writing JSON files
        FileHandler fileHandler = new FileHandler(filePath);

        // Initialize the TaskRepository, which manages tasks, with the FileHandler
        TaskRepository taskRepository = new TaskRepository(fileHandler);

        // Create a TaskService, which provides business logic for task management, using TaskRepository
        TaskService taskService = new TaskService(taskRepository);

        // Instantiate a CLI object that will use TaskService to interact with the user
        CLI cli = new CLI(taskService);

        if (args.length > 0) {
            cli.start(args);
        } else {
            System.out.println("Usage: java Main <command> [options]");
        }
//
    }
}