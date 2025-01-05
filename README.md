# JavaFX To-Do List Application

## Overview
This is a JavaFX-based To-Do List application that allows users to manage their tasks effectively. Users can add tasks, mark them as completed, and view their completed tasks in a history table. The application also includes a search feature to filter completed tasks by title or completion date. 

This project integrates a relational database using JDBC to store and manage tasks, ensuring persistence and efficient data management.

---

## Features
1. **User-Friendly Interface**
   - Modern UI built using JavaFX components such as `StackPane`,`ListView`, `TableView`, `TextField`, `Button`, and `DatePicker`.
   - Simple and intuitive layout for task management.

2. **Task Management**
   - Add tasks with a title and optional description.
   - Mark tasks as completed to move them to the completed tasks table.

3. **Database Integration**
   - Persistent storage using a relational database (JDBC).
   - Two database tables: 
     - **Active Tasks**: Stores tasks that are yet to be completed.
     - **Completed Tasks**: Stores completed tasks along with their completion date.

4. **Search Functionality**
   - Filter and view completed tasks by task title or completion date.

5. **Optional Enhancements**
   - Edit or delete tasks before marking them as completed.
   - Categorize tasks (e.g., Work, Personal, Urgent).

---

## Technologies Used
- **JavaFX**: For creating the user interface.
- **JDBC**: For database interaction.
- **Relational Database**: To store active and completed tasks (MySQL).
- **Java 22**: Core language for implementation.

---
