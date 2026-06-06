// Main.java
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Step 1: Create all required directories
        new File("input").mkdirs();
        new File("output").mkdirs();
        new File("logs").mkdirs();

        // Step 2: Show where Java is looking (helpful for debugging)
        System.out.println("Working directory: " + new File(".").getAbsolutePath());
        System.out.println("Looking for input files in: " + new File("input").getAbsolutePath());

        // Step 3: Auto-create sample .txt files if input folder is empty
        File inputDir = new File("input");
        File[] existingFiles = inputDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (existingFiles == null || existingFiles.length == 0) {
            System.out.println("No input files found. Creating sample files...");
            String[] sampleContent = {
                "Java multithreading allows concurrent execution of two or more parts of a program.\n" +
                "Each part is called a thread.\n" +
                "Threads are lightweight sub-processes.",

                "BufferedReader reads text from a character-input stream.\n" +
                "It buffers characters to provide efficient reading.\n" +
                "readLine() reads one line at a time.",

                "ExecutorService manages a pool of worker threads.\n" +
                "It decouples task submission from execution.\n" +
                "Always call shutdown() after use."
            };

            for (int i = 1; i <= 3; i++) {
                try (BufferedWriter bw = new BufferedWriter(
                        new FileWriter("input/file" + i + ".txt"))) {
                    bw.write(sampleContent[i - 1]);
                    bw.newLine();
                } catch (IOException e) {
                    System.err.println("Could not create sample file " + i + ": " + e.getMessage());
                }
            }
            System.out.println("Sample files created successfully in input/ folder.");
        }

        // Step 4: Re-fetch files after creation
        File[] files = inputDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("Still no input files found. Check folder permissions.");
            return;
        }

        // Step 5: Create thread pool and process files
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Logger.log("=== Processing started. Files found: " + files.length + " ===");

        for (File file : files) {
            String inputPath  = file.getPath();
            String outputPath = "output/processed_" + file.getName();
            executor.submit(new FileProcessorTask(inputPath, outputPath));
        }

        // Step 6: Wait for all threads to complete
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        Logger.log("=== All files processed successfully ===");
    }
}