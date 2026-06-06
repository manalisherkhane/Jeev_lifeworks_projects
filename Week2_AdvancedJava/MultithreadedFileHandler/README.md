# Multithreaded File Handler

## Assignment Overview

This project is a Java-based **Multithreaded File Processing System** developed to demonstrate Advanced Java concepts such as **Multithreading, ExecutorService, File Handling, BufferedReader, BufferedWriter, Logging, and Concurrent File Processing**.

The application processes multiple text files simultaneously using a thread pool, reads data from input files, performs processing operations, writes the processed content to output files, and logs all processing activities.

---

## Technologies Used

* Java 8+
* Multithreading
* ExecutorService
* Runnable Interface
* BufferedReader
* BufferedWriter
* File Handling
* Logging Mechanism
* Git & GitHub

---

## Features Implemented

### File Processing

* Read multiple text files from the input directory
* Process file contents concurrently
* Generate separate output files

### Multithreading

* Process multiple files simultaneously
* Use ExecutorService for thread management
* Fixed-size thread pool implementation

### File Handling

* Read files using BufferedReader
* Write processed content using BufferedWriter
* Automatic file and directory handling

### Logging

* Log processing activities
* Track file processing status
* Record successful completion and errors

### Concurrent Execution

* Independent task execution for each file
* Efficient resource utilization
* Improved performance through parallel processing

---

## Project Structure

```text
MultithreadedFileHandler
│
├── src
│   ├── Main.java
│   ├── FileProcessor.java
│   ├── FileProcessorTask.java
│   └── Logger.java
│
├── input
│   ├── file1.txt
│   ├── file2.txt
│   └── file3.txt
│
├── output
│
├── logs
│   └── processing.log
│
└── README.md
```

---

## Concepts Demonstrated

### Multithreading

* Thread Pool
* Concurrent Execution
* Runnable Interface
* ExecutorService

### File Handling

* BufferedReader
* BufferedWriter
* File Class
* FileReader
* FileWriter

### Java Concurrency

* Task Submission
* Thread Management
* Executor Framework

### Logging

* Activity Tracking
* Error Logging
* Process Monitoring

---

## How to Run

1. Clone the repository.
2. Open the project in VS Code, Eclipse, or IntelliJ IDEA.
3. Ensure Java 8 or higher is installed.
4. Navigate to the project directory.
5. Compile the source files.
6. Run Main.java.

---

## Sample Functionalities

* Read multiple input text files
* Process files concurrently using multiple threads
* Generate processed output files
* Create processing logs
* Monitor task execution status
* Handle file processing errors gracefully

---

## Execution Flow

1. Scan the input directory for text files.
2. Create a thread pool using ExecutorService.
3. Submit each file as an independent processing task.
4. Read file contents using BufferedReader.
5. Process file data.
6. Write results to output files using BufferedWriter.
7. Record activities in processing.log.
8. Shut down the ExecutorService after task completion.

---

## GitHub Repository

Repository Link:

https://github.com/manalisherkhane/Library_Management_Systemss.git
