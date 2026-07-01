# HTTP Interceptors & Authentication Demo

## Assignment Overview

This project is an **Angular-based HTTP Interceptors & Authentication Demo** developed to demonstrate **Angular HTTP Client**, **Functional HTTP Interceptors**, **Route Guards**, **Signal-Based State Management**, **Lazy Loading**, and **REST API Integration** using modern Angular standalone architecture.

The application simulates a secure authentication flow where users log in, access protected routes, and make authenticated API requests while showcasing Angular best practices.

---

## Technologies Used

* Angular 17+
* TypeScript
* HTML5
* CSS3
* Angular Standalone Components
* Angular Signals
* Angular HttpClient
* Functional HTTP Interceptors
* Angular Route Guards
* Angular Router
* Angular Reactive Forms
* RxJS
* JSONPlaceholder REST API
* Git & GitHub
* VS Code

---

## Features Implemented

### Authentication

* User Login
* User Logout
* Token-based Authentication
* Authentication State Management
* Local Storage Token Persistence

### HTTP Interceptors

* Authentication Interceptor
* Automatically Attach Authorization Header
* Global Error Handling
* Handle Unauthorized Requests (401)
* Handle Network Errors
* Handle API Errors

### Route Protection

* Protected Routes
* Authentication Guard
* Redirect Unauthorized Users
* Secure Dashboard Access

### REST API Integration

* Consume External REST API
* Fetch Posts
* Display API Data
* HttpClient Integration

### State Management

* Angular Signals
* Computed Signals
* Reactive Authentication State
* Centralized Auth Service

### User Interface

* Login Screen
* Dashboard
* Protected Navigation
* Responsive Layout

---

## Project Structure

```text
src/app/

├── core
│   ├── interceptors
│   │   ├── auth.interceptor.ts
│   │   └── error.interceptor.ts
│   │
│   ├── guards
│   │   └── auth.guard.ts
│   │
│   └── services
│       ├── auth.service.ts
│       └── api.service.ts
│
├── features
│   ├── login
│   │   └── login.component.ts
│   │
│   └── dashboard
│       └── dashboard.component.ts
│
├── app.routes.ts
├── app.config.ts
├── app.component.ts
└── main.ts
```

---

## Angular Concepts Demonstrated

* Standalone Components
* Angular Signals
* Computed Signals
* Functional HTTP Interceptors
* Route Guards
* Reactive Forms
* Lazy Loading
* Dependency Injection
* Angular HttpClient
* REST API Integration
* Angular Routing
* Authentication Flow
* State Management

---

## Application Architecture

```text
User Login
      │
      ▼
Auth Service
      │
      ▼
Signals State Management
      │
      ├──────────────► Route Guard
      │                     │
      │                     ▼
      │               Protect Routes
      │
      ▼
Auth Interceptor
      │
      ▼
Attach JWT Token
      │
      ▼
HTTP Request
      │
      ▼
REST API
      │
      ▼
Error Interceptor
      │
      ▼
Global Error Handling
```

---

## Installation

Clone the repository.

Navigate to the project directory.

Install dependencies:

```bash
npm install
```

Run the application:

```bash
ng serve
```

Open your browser:

```text
http://localhost:4200
```

---

## Application Routes

| Route        | Description         |
| ------------ | ------------------- |
| `/login`     | User Login          |
| `/dashboard` | Protected Dashboard |
| `/**`        | Redirect to Login   |

---

## Authentication Flow

1. User enters username and password.
2. Authentication service validates credentials.
3. Token is generated and stored in Local Storage.
4. Authentication Signal is updated.
5. Route Guard allows access.
6. HTTP Interceptor attaches Authorization header.
7. API request is sent.
8. Dashboard displays fetched data.

---

## HTTP Interceptors

### Authentication Interceptor

* Reads authentication token
* Adds Authorization header
* Sends authenticated API requests

### Error Interceptor

* Handles HTTP 401 Unauthorized
* Redirects user to Login
* Clears authentication state
* Handles network errors
* Handles API exceptions globally

---

## Route Guard

The application protects secured routes by:

* Checking authentication status
* Redirecting unauthenticated users
* Preventing unauthorized navigation
* Allowing authenticated users only

---

## State Management

The application uses **Angular Signals** for:

* Authentication State
* Token Storage
* Login Status
* Reactive UI Updates
* Centralized Application State

---

## REST API Integration

The application consumes data using Angular HttpClient.

Example API:

```text
https://jsonplaceholder.typicode.com/posts
```

Features:

* Fetch All Posts
* Fetch Post by ID
* Observable-based API Calls
* Strongly Typed Models

---

## Sample Functionalities

* User Login
* User Logout
* Generate Authentication Token
* Store Token in Local Storage
* Protect Routes
* Redirect Unauthorized Users
* Consume REST APIs
* Display API Data
* Attach Authorization Headers
* Global Error Handling
* Signal-Based State Management
* Lazy Loaded Components

---

## Security Features

* Authentication Token
* Authorization Header
* Protected Routes
* Route Guards
* Functional HTTP Interceptors
* Authentication State Management
* Automatic Logout on Unauthorized Access

---

## Application Workflow

```text
Login
   │
   ▼
Authentication Service
   │
   ▼
Generate Token
   │
   ▼
Store Token
   │
   ▼
Signals Updated
   │
   ▼
Navigate to Dashboard
   │
   ▼
HTTP Request
   │
   ▼
Auth Interceptor
   │
   ▼
REST API
   │
   ▼
Dashboard
```

---

## Testing the Application

### Login

Enter any valid username and password.

---

### Protected Route

Navigate to:

```text
/dashboard
```

Unauthenticated users are redirected to:

```text
/login
```

---

### Verify Authorization Header

Open Browser Developer Tools.

Navigate to:

* Network Tab
* Select API Request
* Verify the Authorization Header:

```http
Authorization: Bearer <AUTH_TOKEN>
```

---

### Test Error Handling

Simulate:

* Invalid Token
* Unauthorized Request
* Network Failure

The Error Interceptor will:

* Logout the user
* Redirect to Login
* Display appropriate error handling

---

## Project Highlights

* Angular 17 Standalone Architecture
* Functional HTTP Interceptors
* Route Guards
* Signal-Based State Management
* Angular HttpClient
* REST API Integration
* Authentication Flow
* Global Error Handling
* Lazy Loaded Components
* Clean Component Architecture
* Modern Angular Best Practices