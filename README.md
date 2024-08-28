# Fantasy Team Manager

A brief description of your JavaFX application. Explain what it does and any notable features.

## Features

- List features

## Prerequisites

Before you can build and run this project, ensure you have the following installed:

- Java Development Kit (JDK): Corretto 21 or later.
- Maven
- A MySQL instance

## Getting Started

Follow these steps to get a copy of the project up and running on your local machine.

### Clone the Repository

First, clone the repository to your local machine:

```cmd
git clone https://github.com/cdy-peters/cab302
cd cab302
```

### Set environment variables

Create a .env file in the root folder and set the variables specified in .env.example

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

## Structure

- src/main/java: Contains the source code for the application.
  - .../client: Contains the JavaFX client application.
    - /controllers: Contains the controllers that bind the UI to the application logic.
    - /helpers: Contains helper classes for the JavaFX application.
  - .../server: Contains the Spring Boot server application.
    - /controllers: Contains the controllers that handle HTTP requests.
    - /models: Contains the data models and DAO classes that handle database operations.
- src/main/resources: Contains resources such as FXML files, images and other assets.
- pom.xml: Maven configuration file specifying dependencies and plugins.

## Resources
<!-- https://openjdk.org/groups/net/httpclient/intro.html -->
- [Creating HTTP requests](https://openjdk.org/groups/net/httpclient/intro.html)
- [Restful API guide](https://spring.io/guides/tutorials/rest/)

## Acknowledgments

- Marcus Camaroni
- Cody Peters
- Billy Caslin
- Kris Kong
- Mitchell Wills
