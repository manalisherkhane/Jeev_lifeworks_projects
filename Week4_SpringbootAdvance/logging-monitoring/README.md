# Logging & Monitoring REST API

## Assignment Overview

This project is a **Spring Boot-based Logging & Monitoring REST API** developed to demonstrate centralized logging, request monitoring, Aspect-Oriented Programming (AOP), exception handling, validation, and RESTful web services. The application logs HTTP requests, service-layer activities, exceptions, and audit events using **SLF4J** and **Logback**, while exposing REST APIs for product management.

---

## Technologies Used

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* H2 Database
* Hibernate ORM
* Spring AOP
* SLF4J
* Logback
* Lombok
* Jakarta Validation
* Maven
* Postman
* Git & GitHub

---

## Features Implemented

### Product Management

* Create new products
* View all products
* View product by ID
* Update product details
* Delete products
* Search products by category

### Logging & Monitoring

* Centralized application logging
* Request and response logging
* Service-layer audit logging using Spring AOP
* Request execution time tracking
* Request ID generation using MDC
* Dedicated audit log file
* Console and rolling file logging

### Validation

* Validate product name
* Validate category
* Validate positive price values
* Validate stock quantity

### Exception Handling

* Global exception handling
* Custom exceptions
* ResourceNotFoundException
* BadRequestException
* Validation error handling
* Structured JSON error responses
* Proper HTTP status codes

### Database Operations

* Store product data using H2 Database
* CRUD operations using Spring Data JPA
* Automatic table creation with Hibernate

---

## Project Structure

```
src/main/java/com/assignment/logging/

├── config
│   └── LoggingConfig.java
│
├── controller
│   └── ProductController.java
│
├── service
│   └── ProductService.java
│
├── repository
│   └── ProductRepository.java
│
├── model
│   └── Product.java
│
├── dto
│   └── ProductRequest.java
│
├── exception
│   ├── ResourceNotFoundException.java
│   ├── BadRequestException.java
│   └── GlobalExceptionHandler.java
│
├── aspect
│   └── AuditLoggingAspect.java
│
├── filter
│   └── RequestLoggingFilter.java
│
└── LoggingMonitoringApplication.java

src/main/resources/

├── application.properties
└── logback-spring.xml
```

---

## Spring Boot Concepts Demonstrated

* REST API Development
* Layered Architecture
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* DTO Pattern
* Jakarta Bean Validation
* Global Exception Handling
* Spring AOP
* Request Logging
* Audit Logging
* MDC (Mapped Diagnostic Context)
* Logback Configuration
* CRUD Operations

---

## Database Configuration

The project uses the **H2 In-Memory Database**.

Configure `application.properties`:

```properties
spring.application.name=logging-monitoring

spring.datasource.url=jdbc:h2:mem:loggingdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false

logging.level.root=INFO
logging.level.com.assignment.logging=DEBUG
logging.level.org.springframework.web=WARN

logging.file.name=logs/app.log
```

---

## Logging Configuration

The project uses **Logback** for centralized logging.

### Log Files

* `logs/app.log` – General application logs
* `logs/audit.log` – Audit logs generated using Spring AOP

### Logging Features

* Console logging
* Rolling log files
* Request ID tracking using MDC
* Request execution time logging
* Service method audit logging
* Exception logging

---

## How to Run

1. Clone the repository.

2. Open the project in IntelliJ IDEA, Eclipse, or VS Code.

3. Ensure Java 17 and Maven are installed.

4. Install project dependencies.

5. Run the application:

```bash
mvn spring-boot:run
```

6. Access the API at:

```
http://localhost:8080/api/products
```

7. Access the H2 Console:

```
http://localhost:8080/h2-console
```

---

## API Endpoints

| Method | Endpoint                                 | Description              |
| ------ | ---------------------------------------- | ------------------------ |
| POST   | `/api/products`                          | Create Product           |
| GET    | `/api/products`                          | Get All Products         |
| GET    | `/api/products/{id}`                     | Get Product By ID        |
| GET    | `/api/products/category?name={category}` | Get Products By Category |
| PUT    | `/api/products/{id}`                     | Update Product           |
| DELETE | `/api/products/{id}`                     | Delete Product           |

---

## Sample Request Body

```json
{
  "name": "Laptop",
  "category": "Electronics",
  "price": 75000.00,
  "stock": 10
}
```

---

## Sample Functionalities

* Create products
* Retrieve all products
* Retrieve product by ID
* Filter products by category
* Update product information
* Delete products
* Validate user input
* Handle exceptions gracefully
* Store and retrieve data using H2 Database
* Log every HTTP request
* Track request execution time
* Maintain audit logs for service methods
* Generate request IDs for traceability
* Capture application and exception logs

---

## Logging Components

| Component              | Purpose                                                          |
| ---------------------- | ---------------------------------------------------------------- |
| RequestLoggingFilter   | Logs incoming and outgoing HTTP requests with execution time     |
| AuditLoggingAspect     | Logs all service-layer method calls and execution details        |
| GlobalExceptionHandler | Handles exceptions and logs errors consistently                  |
| logback-spring.xml     | Configures console logging, rolling file logging, and audit logs |
| MDC                    | Generates unique request IDs for request tracing                 |

---

## Testing with cURL

### Create Product

```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name":"Laptop","category":"Electronics","price":75000,"stock":10}'
```

### Get All Products

```bash
curl http://localhost:8080/api/products
```

### Get Product by ID

```bash
curl http://localhost:8080/api/products/1
```

### Trigger Validation Error

```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name":"","price":-100}'
```

### Trigger Resource Not Found

```bash
curl http://localhost:8080/api/products/999
```