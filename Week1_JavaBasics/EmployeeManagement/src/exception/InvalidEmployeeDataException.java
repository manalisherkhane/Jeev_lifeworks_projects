// exception/InvalidEmployeeDataException.java
package exception;

public class InvalidEmployeeDataException extends Exception {
    public InvalidEmployeeDataException(String message) {
        super("Invalid data: " + message);
    }
}