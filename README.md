## 🎯 Overview

A lightweight command-line task tracker application implemented in Java. This tool allows users to manage their tasks by
adding, updating, deleting, and viewing tasks, with data stored in a JSON file for persistence

**Note:** This project is a sample solution for the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge
from the [roadmap.sh](https://roadmap.sh/)

## ✨ Features

- **Task Management:** Add, update, and delete tasks easily
- **Task Listing:** List all tasks, with options to filter by status
- **Task Status Management:** Mark tasks with statuses: in-progress and done
- **Command-line Interface** Accepts commands and arguments for various operations

## 🚀 How to Run

1. Clone the repository
    ```bash
    git clone https://github.com/cdparkes/TaskTrackerCLI
    cd TaskTrackerCLI/taskTrackerCLI/src
    ```
   
2. Compile the code
    ```bash
   javac TaskCLI.java
   ```   

## 📘 Usage Example

```bash
# Adding a new task
java TaskCLI add Buy groceries

# Updating and deleting tasks
java TaskCLI update 1 Buy groceries and cook dinner
java TaskCLI delete 1

# Marking a task as in progress or done
java TaskCLI mark-in-progress 1
java TaskCLI mark-done 1

# Listing all tasks
java TaskCLI list

# Listing tasks by status
java TaskCLI list done
java TaskCLI list todo
java TaskCLI list in-progress
```
