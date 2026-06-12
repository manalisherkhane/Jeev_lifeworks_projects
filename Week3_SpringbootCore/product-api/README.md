# Product Management REST API

## Assignment Overview

This project is a Spring Boot-based Product Management REST API developed to demonstrate RESTful web services, Layered Architecture, Spring Data JPA, Exception Handling, Validation, and MySQL database integration.

## Technologies Used

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* MySQL Database
* Hibernate ORM
* Lombok
* Jakarta Validation
* Maven
* Postman
* Git & GitHub

## Features Implemented

### Product Management

* Create new products
* View all products
* View product by ID
* Update product details
* Delete products

### Product Search

* Search product by ID
* Filter products by category
* Retrieve product information

### Database Operations

* Store product data in MySQL database
* Perform CRUD operations using Spring Data JPA
* Automatic table creation using Hibernate

### Validation

* Validate product name
* Validate category
* Validate positive price values
* Validate stock quantity

### Exception Handling

* Custom exception classes
* ProductNotFoundException
* GlobalExceptionHandler
* Validation error handling
* Proper HTTP status code responses

## Project Structure

```text
src/main/java/com/assignment/productapi/

├── controller
│   └── ProductController.java

├── service
│   ├── ProductService.java
│   └── ProductServiceImpl.java

├── repository
│   └── ProductRepository.java

├── model
│   └── Product.java

├── dto
│   └── ProductDTO.java

├── exception
│   ├── ProductNotFoundException.java
│   └── GlobalExceptionHandler.java

└── ProductApiApplication.java
```

## Spring Boot Concepts Demonstrated

* REST API Development
* Layered Architecture
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* DTO Pattern
* Validation using Jakarta Validation
* Exception Handling
* MySQL Database Integration
* CRUD Operations

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE product_db;
```

Configure `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
```

## How to Run

1. Clone the repository.

2. Open the project in IntelliJ IDEA, Eclipse, or VS Code.

3. Ensure Java 17 and MySQL are installed.

4. Create the database:

   ```
   CREATE DATABASE product_db;
   ```

5. Update database credentials in `application.properties`.

6. Install dependencies and run the application:

   ```
   mvn spring-boot:run
   ```

7. Access the API at:

   ```
   http://localhost:8080/api/products
   ```

## API Endpoints

| Method | Endpoint                          | Description              |
| ------ | --------------------------------- | ------------------------ |
| POST   | /api/products                     | Create Product           |
| GET    | /api/products                     | Get All Products         |
| GET    | /api/products/{id}                | Get Product By ID        |
| GET    | /api/products/category/{category} | Get Products By Category |
| PUT    | /api/products/{id}                | Update Product           |
| DELETE | /api/products/{id}                | Delete Product           |

## Sample Request Body

```json
{
  "name": "Laptop",
  "category": "Electronics",
  "price": 75000.0,
  "stock": 50
}
```

## Sample Functionalities

* Create products
* Retrieve all products
* Retrieve product by ID
* Filter products by category
* Update product information
* Delete products
* Validate user input
* Handle exceptions gracefully
* Store and retrieve data from MySQL

## GitHub Repository

Repository Link: https://github.com/manalisherkhane/Library_Management_Systemss.git
