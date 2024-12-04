package handlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class representing a task with attributes and behaviors
public class Task implements Comparable<Task>{
    private Long id; // Unique identifier for the task
    private String description; // Description of the task
    private StatusHandler status; // Current status of the task
    private LocalDateTime createdAt; // Timestamp when the task was created
    private LocalDateTime updatedAt; // Timestamp when the task was last updated

    public Task() {}

    // Constructor to initialize a new task with a description and default status
    public Task(String description) {
        this.description = description;
        this.status = StatusHandler.TODO; // Default status
        this.createdAt = LocalDateTime.now(); // Capture creation time
        this.updatedAt = LocalDateTime.now(); // Set updated time to creation time
    }

    // Getter and setter methods for task properties

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusHandler getStatus() {
        return status;
    }

    public void setStatus(StatusHandler status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Compare tasks based on their ID for sorting or comparison purposes
    @Override
    public int compareTo(Task task) {
        return id.compareTo(task.getId());
    }

    // Override toString method to provide a readable representation of the task
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String createdAtFormatted = (createdAt != null) ? createdAt.format(formatter) : "N/A";
        String updatedAtFormatted = (updatedAt != null) ? updatedAt.format(formatter) : "N/A";
        return String.format("Task{ id= %d, description= %s, status= %s, created at= %s, updated at= %s%n",
                id, description, status != null ? status: "N/A", createdAtFormatted, updatedAtFormatted);
    }
}
