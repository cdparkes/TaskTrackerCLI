package handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// FileHandler manages read/write operations for a specified file path
public class FileHandler {
    private final String filePath; // Path to the file for storing data

    // Constructor initializes the file path and ensures necessary directories and file exist
    public FileHandler(String filePath) {
        this.filePath = filePath;
        createDirectoryIfNotExist(); // Ensure the directory exists
        createFileIfNotExist(); // Ensure the file exists
    }

    // Creates directory if it doesn't exist
    private void createDirectoryIfNotExist() {
        try {
          File file = new File(filePath.substring(0, filePath.lastIndexOf('/')));
            file.mkdirs(); // Attempt to create the directory
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Print error messages in case an exception get thrown
        }
    }

    // Creates the file if it doesn't exist
    private void createFileIfNotExist() {
        try {
            File file = new File(filePath);
            file.createNewFile(); // Attempt to create the file
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Print error messages
        }
    }

    // Reads all lines from the file and returns them as a list of strings
    public List<String> readAllLines() {
        try {
            return Files.readAllLines(Paths.get(filePath)); // Read file content
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Handle and print exceptions
        }
        return new ArrayList<>(); // Return an empty list if reading fails
    }

    // Writes JSON string to the file
    public void writeJsonToFile(String json) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(json); // Write JSON content to file
            bufferedWriter.close(); // Close the writer
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Print error messages
        }
    }
}
