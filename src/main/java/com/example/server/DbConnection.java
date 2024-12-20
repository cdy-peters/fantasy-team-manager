package com.example.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for creating a database connection.
 */
public class DbConnection {
    private static Connection conn = null;

    /**
     * Create a new DbConnection instance.
     */
    private DbConnection() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:" + Server.dotenv.get("MYSQL_URL"),
                    Server.dotenv.get("MYSQL_USER"),
                    Server.dotenv.get("MYSQL_PASSWORD"));
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Get the database connection.
     * 
     * @return The database connection.
     */
    public static Connection getConnection() {
        if (conn == null) {
            new DbConnection();
        }
        return conn;
    }
}
