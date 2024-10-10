package com.example.server;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Entry point for the server application.
 */
@SpringBootApplication
public class Server {
    /** Load the .env file */
    public static Dotenv dotenv = Dotenv.load();
    /** Get the database connection */
    public static Connection conn = DbConnection.getConnection();

    /**
     * Default constructor
     */
    public Server() {
    }

    /**
     * Start the application.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
