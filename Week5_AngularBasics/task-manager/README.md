# Angular Task Manager

## Assignment Overview

This project is an **Angular-based Task Manager Application** developed to demonstrate modern Angular concepts including **Standalone Components**, **Reactive Forms**, **Signals**, **Lazy Loading**, **Routing**, **CRUD Operations**, **State Management**, and **Responsive UI Design**.

The application enables users to create, view, update, delete, search, and filter tasks while showcasing Angular best practices and clean component architecture.

---

## Technologies Used

* Angular 17+
* TypeScript
* HTML5
* CSS3
* Angular Signals
* Angular Reactive Forms
* Angular Router
* Standalone Components
* Lazy Loading
* VS Code
* Git & GitHub

---

## Features Implemented

### Task Management

* Create new tasks
* View all tasks
* View task details
* Update existing tasks
* Delete tasks

### Task Organization

* Search tasks
* Filter tasks by status
* Filter tasks by priority
* View task statistics
* Task detail page

### Form Validation

* Required field validation
* Minimum length validation
* Maximum length validation
* Inline validation error messages
* Reactive Form validation

### Routing

* Lazy-loaded routes
* Route navigation
* Dynamic route parameters
* Task detail routing
* Edit task routing

### State Management

* Angular Signals
* Computed Signals
* Read-only Signals
* In-memory task management

### User Interface

* Responsive Design
* Modern Dashboard
* Task Statistics Cards
* Search Bar
* Filter Controls
* Professional UI Layout

---

## Project Structure

```text
src/app/

├── models
│   └── task.model.ts
│
├── services
│   └── task.service.ts
│
├── components
│   ├── task-list
│   │   ├── task-list.component.ts
│   │   ├── task-list.component.html
│   │   └── task-list.component.css
│   │
│   ├── task-form
│   │   ├── task-form.component.ts
│   │   ├── task-form.component.html
│   │   └── task-form.component.css
│   │
│   └── task-detail
│       ├── task-detail.component.ts
│       ├── task-detail.component.html
│       └── task-detail.component.css
│
├── app.routes.ts
├── app.config.ts
├── app.component.ts
├── app.component.html
└── app.component.css
```

---

## Angular Concepts Demonstrated

* Standalone Components
* Angular Signals
* Computed Signals
* Reactive Forms
* Form Validation
* Lazy Loading
* Angular Routing
* Dynamic Routing
* Dependency Injection
* Component Architecture
* State Management
* CRUD Operations
* TypeScript Interfaces
* Angular Control Flow (@if, @for)

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

Open your browser and visit:

```text
http://localhost:4200
```

---

## Application Routes

| Route             | Description        |
| ----------------- | ------------------ |
| `/tasks`          | View All Tasks     |
| `/tasks/new`      | Create New Task    |
| `/tasks/:id`      | View Task Details  |
| `/tasks/:id/edit` | Edit Existing Task |

---

## Task Model

```typescript
export interface Task {
  id: number;
  title: string;
  description: string;
  status: 'pending' | 'in-progress' | 'completed';
  priority: 'low' | 'medium' | 'high';
  dueDate: string;
  createdAt: string;
}
```

---

## Sample Functionalities

* Create new tasks
* Edit existing tasks
* Delete tasks
* View task details
* Search tasks
* Filter by status
* Filter by priority
* Display task statistics
* Validate user input
* Responsive user interface
* Lazy-loaded navigation
* Real-time state updates using Signals

---

## Task Statistics

The dashboard displays:

* Total Tasks
* Pending Tasks
* In Progress Tasks
* Completed Tasks

---

## Form Validation

The application validates:

* Task Title
* Task Description
* Task Status
* Task Priority
* Due Date

Validation includes:

* Required Fields
* Minimum Character Length
* Maximum Character Length
* Inline Error Messages

---

## User Interface Features

* Modern Dashboard
* Responsive Layout
* Task Cards
* Search Functionality
* Status Filters
* Priority Filters
* Statistics Cards
* Detail View
* Clean Navigation
* Interactive Buttons

---

## State Management

The application uses Angular Signals for:

* Managing task data
* Computing task statistics
* Updating UI reactively
* Filtering tasks
* Read-only application state

---

## Application Workflow

1. Create a new task.
2. View all available tasks.
3. Search tasks by title or description.
4. Filter tasks using status and priority.
5. View task details.
6. Edit task information.
7. Delete completed or unwanted tasks.
8. Dashboard statistics update automatically.

---

## Key Angular Features

* Standalone Components
* Reactive Forms
* Angular Signals
* Computed Signals
* Lazy Loading
* Angular Router
* Dynamic Routes
* TypeScript Interfaces
* Component Communication
* Dependency Injection

---

## Project Highlights

* Modern Angular 17+ Architecture
* Standalone Components
* Signal-Based State Management
* CRUD Operations
* Lazy Loaded Routing
* Reactive Forms with Validation
* Responsive User Interface
* Search and Filtering
* Task Statistics Dashboard
* Clean and Maintainable Code Structure