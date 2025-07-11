# Library Management System

A full-stack web application for managing a college library system. Built using Spring Boot (backend) and React (frontend), the system supports administrators in managing book inventories and allows students to reserve books based on their academic year. 

## Features

- Admin functionalities:
  - Add, update, and delete books
  - Set total and available copies
  - Categorize books by academic year

- Student functionalities:
  - Register and log in securely
  - View available books
  - Reserve books (based on availability)

- Role-based access control using Spring Security
- Real-time book availability tracking
- Backend and frontend communication via REST APIs

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

### Frontend
- React
- Axios
- Bootstrap and Tailwind CSS
- REST API integration

## Project Structure
library-management/
│
├── backend/
│ ├── src/main/java/com/example/library
│ ├── resources/
│ └── pom.xml
│
├── frontend/
│ ├── public/
│ ├── src/
│ └── package.json



