# F1 Race Management System

## Overview

This project is a backend application developed using Java and Spring Boot, aimed at managing various aspects of a Formula 1 racing environment. The system allows for the management of drivers, teams (constructors), circuits, and races. It serves as a foundational project to showcase basic CRUD operations, service layer logic, and data persistence.

## Project Structure

The project is organized into the following main packages:

- **Configurations**: Contains configuration files and classes used for setting up the Spring Boot application, such as database configurations and other beans.

- **Controllers**: The REST controllers that handle HTTP requests and map them to the appropriate service methods. They expose endpoints for managing the entities like Drivers, Teams, Circuits, and Races.

- **DTOs (Data Transfer Objects)**: This package contains classes that are used to transfer data between the client and server, ensuring that only the required information is shared.

- **Mappers**: This package includes classes responsible for mapping between entities and DTOs, ensuring the separation of concerns and enabling cleaner code.

- **POJOs (Plain Old Java Objects)**: These are the entity classes that represent the domain objects in the system such as Driver, Constructor, Circuit, Race, and RaceType. Each class is mapped to a database table using JPA annotations.

- **Repositories**: Contains interfaces that extend JPA repositories. These interfaces provide methods for CRUD operations and custom queries to interact with the database.

- **Requests**: This package holds the classes that define the structure of incoming requests, encapsulating the input data for various operations.

- **Services**: The service layer contains business logic and interacts with repositories to perform operations. Services are called by controllers to handle incoming requests.

## Additional Files

- **Main.java**: The entry point of the Spring Boot application.

- **docker-compose.yml**: A Docker Compose file that can be used to set up and run the application along with necessary services like databases.

- **application.yml**: The main configuration file for the Spring Boot application, including database connection settings, server port, and other environmental configurations.

## Testing

The project includes unit tests to ensure the reliability and correctness of the repository and service layers:

- **CircuitRepositoryTest.java**: Contains test cases for the Circuit repository to validate database interactions.
  
- **CircuitServiceTest.java**: Contains test cases for the Circuit service to validate business logic.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker (optional, for running with Docker Compose)

### Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
2. **Navigate to the project directory:**:
   ```bash
   cd F1RaceManagement
3. **Build the project using Maven:**:
   ```bash
   mvn clean install
4. **Run the application:**:
   ```bash
   mvn spring-boot:run

Alternatively, if using Docker Compose:
  ```bash
  docker-compose up --build

   
