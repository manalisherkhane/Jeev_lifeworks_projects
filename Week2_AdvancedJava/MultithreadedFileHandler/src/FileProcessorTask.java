// FileProcessorTask.java
import java.io.*;

public class FileProcessorTask implements Runnable {
    private String inputPath;
    private String outputPath;

    public FileProcessorTask(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    @Override
    public void run() {
        Logger.log("Starting processing: " + inputPath);
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))
        ) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                // Example processing: convert to uppercase
                writer.write(line.toUpperCase());
                writer.newLine();
                lineCount++;
            }
            Logger.log("Completed: " + inputPath + " | Lines processed: " + lineCount);
        } catch (IOException e) {
            Logger.log("ERROR processing " + inputPath + ": " + e.getMessage());
        }
    }
}