// exception/EmployeeNotFoundException.java
package exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(int id) {
        super("Employee with ID " + id + " was not found.");
    }
}