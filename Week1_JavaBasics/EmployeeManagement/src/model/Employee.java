package model;

/**
 * Represents an Employee entity.
 */
public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Dept: %-10s | Salary: %.2f",
                id, name, department, salary);
    }
}