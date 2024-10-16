package com.example.server;

import com.example.server.util.CsvChangeDetector;
import com.example.server.util.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

/**
 * Entry point for the server application.
 */
@SpringBootApplication
public class Server {
    /** Load the .env file */
    public static Dotenv dotenv = Dotenv.load();
    /** Get the database connection */
    public static Connection conn;

    @Autowired
    private CsvChangeDetector csvChangeDetector;

    @Autowired
    private DataImporter dataImporter;

    private static ConfigurableApplicationContext springContext;

    /**
     * Default constructor
     */
    public Server() {
        conn = DbConnection.getConnection();
    }

    /**
     * Start the application.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        springContext = SpringApplication.run(Server.class, args);
    }

    /**
     * Event listener that runs after the application context is refreshed.
     * Checks if the CSV file has changed and imports data if necessary.
     * 
     * @param event The ContextRefreshedEvent
     */
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (csvChangeDetector.hasFileChanged()) {
            dataImporter.importData();
        }
    }

    /**
     * Closes the application context when the application is stopping.
     */
    public void stop() {
        if (springContext != null) {
            springContext.close();
        }
    }
}