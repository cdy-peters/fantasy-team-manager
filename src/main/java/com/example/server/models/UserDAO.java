package com.example.server.models;

import java.sql.Statement;

import com.example.interfaces.IUser;
import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Data Access Object for the user.
 */
public class UserDAO {

    /**
     * Default constructor.
     */
    public UserDAO() {
    }

    /**
     * Find a user by username.
     * 
     * @param username The username
     * @return An IUser object
     */
    public IUser findByUsername(String username) {
        String query = String.format("SELECT * FROM user WHERE username = '%s' LIMIT 1", username);
        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new IUser(id, name, email, username, password);
            } else {
                System.out.println("No user found");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create a new user.
     * 
     * @param user The IUser object
     * @return The user ID
     * @throws SQLIntegrityConstraintViolationException If the username or email is
     *                                                  already taken
     * @throws Exception                                If the user cannot be
     *                                                  created
     */
    public Long create(IUser user) throws SQLIntegrityConstraintViolationException, Exception {
        String name = user.getName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        String query = String.format(
                "INSERT INTO user (name, email, username, password) VALUES ('%s', '%s', '%s', '%s')",
                name, email, username, password);
        try {
            Statement stmt = Server.conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new Exception("Error creating user");
    }

}