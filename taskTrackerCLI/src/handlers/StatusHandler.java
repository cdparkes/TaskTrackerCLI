package handlers;

// Enum to represent the status of tasks
public enum StatusHandler {
    TODO("todo"), // Task is yet to be started
    IN_PROGRESS("in-progress"), // Task is currently in progress
    DONE("done"); // Task is completed

    public final String label; // Label for the status

    // Constructor to assign a label to each enum constant
    StatusHandler(String label) {
        this.label = label;
    }

    // Method to get the corresponding enum constant by its label
    public static StatusHandler getEnumByLabel(String input) {
        for (StatusHandler statusHandler : StatusHandler.values()) {
            if (statusHandler.label.equals(input)) {
                return statusHandler; // Return matching enum constant
            }
        }
        return null; // Return null if no match is found
    }
}
