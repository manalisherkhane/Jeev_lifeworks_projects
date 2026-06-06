// Logger.java
import java.io.*;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "logs/processing.log";

    // synchronized = only one thread writes at a time (thread-safe)
    public static synchronized void log(String message) {
        String entry = LocalDateTime.now() + " [" 
                      + Thread.currentThread().getName() + "] " + message;
        System.out.println(entry);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(LOG_FILE, true))) {  // true = append mode
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}