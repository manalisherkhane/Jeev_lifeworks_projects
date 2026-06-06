# Multithreaded File Handler

## Setup
1. Clone the repo: `git clone <your-repo-url>`
2. Compile: `javac src/*.java -d out/`
3. Run: `java -cp out Main`

## Project Structure
- `src/` - Java source files
- `input/` - Place .txt files here to process
- `output/` - Processed files appear here
- `logs/` - Processing log file

## How It Works
Uses a thread pool (ExecutorService) to process multiple files
simultaneously. Each file is handled by a FileProcessorTask (Runnable).
Results are logged with timestamps and thread names.

## Clean Code Practices Followed
- Single Responsibility Principle (each class has one job)
- Synchronized logging to prevent race conditions
- Try-with-resources for automatic resource cleanup
- Meaningful variable and method names