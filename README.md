
# Fantasy Team Manager

Fantasy Team Manager is a JavaFX application that allows users to manage a fantasy sports team. The application includes both a client-side UI built using JavaFX and a server-side backend powered by Spring Boot and MySQL.

## Features

- Manage players and teams.
- Track player statistics and ratings.
- Real-time updates from the server.
- Intuitive user interface.

## Prerequisites

Before you can build and run this project, ensure you have the following installed:

- **Java Development Kit (JDK):** [Corretto 21 or later](https://aws.amazon.com/corretto/).
- **Maven:** [Download Maven](https://maven.apache.org/download.cgi).
- **MySQL instance:** [Download MySQL](https://dev.mysql.com/downloads/mysql/).

Make sure you have the above tools added to your system’s environment variables (e.g., PATH) to use their commands from any terminal or command prompt.

### For Windows Users:
If you have Windows Terminal installed, you can now launch the application using a batch script.

## Getting Started

Follow these steps to get a copy of the project up and running on your local machine.

### Clone the Repository

First, clone the repository to your local machine:

```cmd
git clone https://github.com/cdy-peters/cab302
cd cab302
```

### Set environment variables

Create a `.env` file in the root folder and set the variables specified in `.env.example`.

### Build the Project

Use Maven to clean and build the project:

```cmd
mvn clean package
```

### Run the Application

Execute the following commands in separate terminal windows to run the JavaFX and Spring Boot applications:

#### JavaFX (client) application

```cmd
mvn javafx:run
```

#### Spring Boot (server) application

```cmd
mvn spring-boot:run
```

### Batch Script for Windows Users

For Windows users with Windows Terminal installed, you can launch the server and client using the provided batch script. Just ensure that Maven commands are accessible via your system's environment variables.

## Project Structure

- **src/main/java:** Contains the source code for the application.
  - **/client:** Contains the JavaFX client application.
    - **/controllers:** Controllers that bind the UI to the application logic.
    - **/helpers:** Helper classes for the JavaFX application.
  - **/server:** Contains the Spring Boot server application.
    - **/controllers:** Handles HTTP requests.
    - **/models:** Data models and DAO classes for database operations.
    - **/util:** Utility classes for server-side operations.
  - **/utils:** Shared utility classes for both client and server operations.
  
- **src/main/resources:** Contains resources such as FXML files, images, and other assets.
- **src/test/java:** Contains test classes for the application.
  - **/com/example/server:** Unit and integration tests for the server-side logic.
  - **/com/example/utils:** Tests for the shared utility classes.
  
- **src/test/resources:** Contains test resources, configuration files, and data required for running tests.

- **pom.xml:** Maven configuration file specifying dependencies and plugins.

## Resources

- [Creating HTTP requests](https://openjdk.org/groups/net/httpclient/intro.html)
- [Restful API guide](https://spring.io/guides/tutorials/rest/)

## Acknowledgments

- Marcus Camaroni
- Cody Peters
- Billy Caslin
- Kris Kong
- Mitchell Wills
