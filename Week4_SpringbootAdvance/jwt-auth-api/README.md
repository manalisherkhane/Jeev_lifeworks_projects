# JWT Authentication REST API

## Assignment Overview

This project is a **Spring Boot-based JWT Authentication REST API** developed to demonstrate **Spring Security**, **JWT (JSON Web Token) Authentication**, **Role-Based Authorization**, **RESTful Web Services**, **Spring Data JPA**, **Global Exception Handling**, **Validation**, and **MySQL Database Integration**.

The application allows users to register, authenticate using JWT, and securely access protected endpoints based on their assigned roles (USER or ADMIN).

---

# Technologies Used

* Java 17
* Spring Boot 3
* Spring Web
* Spring Security
* Spring Data JPA
* MySQL Database
* Hibernate ORM
* JWT (JSON Web Token)
* Lombok
* Jakarta Validation
* Maven
* Postman
* Git & GitHub

---

# Features Implemented

## User Authentication

* User Registration
* User Login
* Password Encryption using BCrypt
* JWT Token Generation
* JWT Token Validation
* Stateless Authentication

## Authorization

* Role-Based Access Control
* USER Role
* ADMIN Role
* Protected API Endpoints
* Authorization using JWT Token

## User Management

* Register New Users
* Authenticate Existing Users
* Store User Details in MySQL
* Retrieve User Information

## Security

* Spring Security Configuration
* Custom JWT Authentication Filter
* UserDetailsService Implementation
* Password Encoding using BCryptPasswordEncoder
* Authentication Manager Configuration

## Validation

* Validate User Name
* Validate Email Address
* Validate Password
* Request Body Validation using Jakarta Validation

## Exception Handling

* Global Exception Handler
* Invalid Credentials Handling
* JWT Expired Token Handling
* Invalid JWT Token Handling
* Access Denied Handling
* Validation Error Handling
* Proper HTTP Status Code Responses

---

# Project Structure

```text
src/main/java/com/example/jwtdemo/

├── config
│   ├── ApplicationConfig.java
│   └── SecurityConfig.java
│
├── controller
│   ├── AuthController.java
│   └── DemoController.java
│
├── dto
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   └── RegisterRequest.java
│
├── exception
│   ├── ApiError.java
│   └── GlobalExceptionHandler.java
│
├── filter
│   └── JwtAuthFilter.java
│
├── model
│   ├── Role.java
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
├── service
│   ├── AuthService.java
│   ├── JwtService.java
│   └── UserDetailsServiceImpl.java
│
└── JwtDemoApplication.java
```

---

# Spring Boot Concepts Demonstrated

* REST API Development
* Layered Architecture
* Dependency Injection
* Spring Security
* JWT Authentication
* Stateless Authentication
* Role-Based Authorization
* UserDetailsService
* Password Encryption using BCrypt
* Spring Data JPA
* Hibernate ORM
* DTO Pattern
* Jakarta Bean Validation
* Global Exception Handling
* MySQL Database Integration

---

# Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE jwt_demo;
```

Configure `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_demo
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

---

# How to Run

1. Clone the repository.

2. Open the project in IntelliJ IDEA, Eclipse, or VS Code.

3. Ensure Java 17 and MySQL are installed.

4. Create the database:

```sql
CREATE DATABASE jwt_demo;
```

5. Update database credentials inside `application.properties`.

6. Install dependencies and run the application:

```bash
mvn spring-boot:run
```

7. Access the application at:

```
http://localhost:8080
```

---

# Authentication Flow

1. Register a new user.
2. Login using registered credentials.
3. Receive a JWT Token.
4. Include the JWT Token in the Authorization Header:

```text
Authorization: Bearer <JWT_TOKEN>
```

5. Access secured APIs.

---

# API Endpoints

| Method | Endpoint             | Description         |
| ------ | -------------------- | ------------------- |
| POST   | `/api/auth/register` | Register User       |
| POST   | `/api/auth/login`    | Login User          |
| GET    | `/api/user`          | USER & ADMIN Access |
| GET    | `/api/admin`         | ADMIN Only Access   |

---

# Sample Register Request

```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
}
```

---

# Sample Login Request

```json
{
    "email": "john@example.com",
    "password": "password123"
}
```

---

# Sample Login Response

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

# Authorization Header

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# Sample Functionalities

* Register new users
* Login using email and password
* Generate JWT Token
* Validate JWT Token
* Secure REST APIs
* Restrict APIs using Roles
* Encrypt Passwords
* Authenticate Users
* Store Users in MySQL Database
* Handle Exceptions Gracefully
* Validate User Input
* Protect APIs using Spring Security

---

# Security Features

* JWT Authentication
* Spring Security Filter Chain
* Stateless Session Management
* BCrypt Password Encryption
* Authentication Manager
* Custom JWT Filter
* Role-Based Authorization
* Protected Endpoints

---

# Exception Handling

The application handles the following exceptions:

* Invalid Credentials
* User Not Found
* Invalid JWT Token
* Expired JWT Token
* Access Denied
* Validation Errors
* Internal Server Errors

Each exception returns a structured JSON response with the appropriate HTTP status code.

---

# Testing Using Postman

## Register User

**POST**

```
/api/auth/register
```

Body:

```json
{
    "name":"John",
    "email":"john@example.com",
    "password":"password123"
}
```

---

## Login User

**POST**

```
/api/auth/login
```

Body:

```json
{
    "email":"john@example.com",
    "password":"password123"
}
```

---

## Access User Endpoint

**GET**

```
/api/user
```

Headers:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## Access Admin Endpoint

**GET**

```
/api/admin
```

Headers:

```text
Authorization: Bearer <JWT_TOKEN>
```

A user with the **USER** role will receive **403 Forbidden**, while a user with the **ADMIN** role can access the endpoint successfully.

---

# Project Highlights

* Secure REST API using JWT Authentication
* Stateless Authentication Mechanism
* Role-Based Authorization (USER & ADMIN)
* Spring Security Integration
* MySQL Database Integration
* Password Encryption with BCrypt
* Global Exception Handling
* Request Validation
* Layered Architecture
* Clean and Maintainable Code Structure