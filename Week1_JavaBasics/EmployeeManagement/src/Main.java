import exception.EmployeeNotFoundException;
import exception.InvalidEmployeeDataException;
import service.EmployeeService;

import java.util.Scanner;

/**
 * Entry point — console-driven Employee Management System.
 */
public class Main {
    private static final EmployeeService service = new EmployeeService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> service.listAll();
                    case 3 -> searchEmployee();
                    case 4 -> filterByDept();
                    case 5 -> updateSalary();
                    case 6 -> deleteEmployee();
                    case 7 -> System.out.printf("Average Salary: %.2f%n",
                                service.getAverageSalary());
                    case 0 -> { System.out.println("Goodbye!"); running = false; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (EmployeeNotFoundException | InvalidEmployeeDataException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n--- Menu ---
                1. Add Employee
                2. List All
                3. Search by ID
                4. Filter by Department
                5. Update Salary
                6. Delete Employee
                7. Average Salary
                0. Exit""");
    }

    private static void addEmployee() throws InvalidEmployeeDataException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        double salary = readDouble("Salary: ");
        service.addEmployee(name, dept, salary);
    }

    private static void searchEmployee() throws EmployeeNotFoundException {
        int id = readInt("Enter Employee ID: ");
        System.out.println(service.getEmployee(id));
    }

    private static void filterByDept() {
        System.out.print("Department name: ");
        String dept = scanner.nextLine();
        var list = service.getByDepartment(dept);
        if (list.isEmpty()) System.out.println("No employees in " + dept);
        else list.forEach(System.out::println);
    }

    private static void updateSalary()
            throws EmployeeNotFoundException, InvalidEmployeeDataException {
        int id = readInt("Employee ID: ");
        double salary = readDouble("New Salary: ");
        service.updateSalary(id, salary);
    }

    private static void deleteEmployee() throws EmployeeNotFoundException {
        int id = readInt("Employee ID to delete: ");
        service.deleteEmployee(id);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }
}