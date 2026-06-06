# Employee Management System

## Assignment Overview

This project is a Java-based Employee Management System developed to demonstrate Object-Oriented Programming (OOP) concepts, Java Collections Framework, Exception Handling, and Java 8 features such as Optional, Streams, and Lambda Expressions.

## Technologies Used

* Java 8+
* Collections Framework (HashMap)
* Exception Handling
* Java Optional API
* Java Streams API
* Lambda Expressions
* Git & GitHub

## Features Implemented

### Employee Management

* Add employees
* Delete employees
* Update employee salary
* View all employees

### Employee Search

* Search employee by ID using Optional
* Retrieve employee details
* Handle employee not found scenarios

### Department Management

* Filter employees by department
* Display department-wise employee records

### Salary Operations

* Update employee salary
* Calculate average salary using Streams API

### Exception Handling

* Custom exception classes
* EmployeeNotFoundException
* InvalidEmployeeDataException
* Validation of invalid employee operations

## Project Structure

```text
src
├── Main.java
├── model
│   └── Employee.java
├── service
│   └── EmployeeService.java
├── exception
│   ├── EmployeeNotFoundException.java
│   └── InvalidEmployeeDataException.java
└── util
    └── InputValidator.java
```

## OOP Concepts Demonstrated

* Encapsulation
* Classes and Objects
* Abstraction
* Exception Handling
* Collection Framework Usage
* Java Optional API
* Java Streams API
* Lambda Expressions

## How to Run

1. Clone the repository.
2. Open the project in VS Code, Eclipse, or IntelliJ IDEA.
3. Ensure Java 8 or higher is installed.
4. Compile the project:

   ```
   javac -d out src/model/*.java src/exception/*.java src/util/*.java src/service/*.java src/Main.java
   ```
5. Run the application:

   ```
   java -cp out Main
   ```

## Sample Functionalities

* Add new employees
* View all employees
* Search employee by ID
* Filter employees by department
* Update employee salary
* Delete employee records
* Calculate average salary
* Handle invalid employee operations through custom exceptions

## GitHub Repository

Repository Link: https://github.com/manalisherkhane/Library_Management_Systemss.git

Library_Management_Systemss
