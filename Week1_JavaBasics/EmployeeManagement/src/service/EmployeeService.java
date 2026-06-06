package service;

import exception.EmployeeNotFoundException;
import exception.InvalidEmployeeDataException;
import model.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class handling all employee operations.
 */
public class EmployeeService {
    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private int nextId = 1;

    /** Add a new employee */
    public void addEmployee(String name, String department, double salary)
            throws InvalidEmployeeDataException {
        if (name == null || name.isBlank())
            throw new InvalidEmployeeDataException("Name cannot be empty.");
        if (salary < 0)
            throw new InvalidEmployeeDataException("Salary cannot be negative.");

        Employee emp = new Employee(nextId++, name, department, salary);
        employeeMap.put(emp.getId(), emp);
        System.out.println("✔ Employee added: " + emp);
    }

    /** Find employee by ID using Optional */
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    /** Get employee or throw custom exception */
    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        return findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /** List all employees using Streams */
    public void listAll() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        employeeMap.values().stream()
                .sorted(Comparator.comparingInt(Employee::getId))
                .forEach(System.out::println);
    }

    /** Filter by department using Streams */
    public List<Employee> getByDepartment(String dept) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }

    /** Get average salary using Streams */
    public double getAverageSalary() {
        return employeeMap.values().stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    /** Delete employee */
    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        getEmployee(id); // throws if not found
        employeeMap.remove(id);
        System.out.println("✔ Employee " + id + " deleted.");
    }

    /** Update salary */
    public void updateSalary(int id, double newSalary)
            throws EmployeeNotFoundException, InvalidEmployeeDataException {
        if (newSalary < 0) throw new InvalidEmployeeDataException("Salary cannot be negative.");
        Employee emp = getEmployee(id);
        emp.setSalary(newSalary);
        System.out.println("✔ Salary updated: " + emp);
    }
}