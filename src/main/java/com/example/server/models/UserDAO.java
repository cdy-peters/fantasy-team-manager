package com.example.server.models;

import java.sql.Statement;

import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDAO {

    public IUser findByUsername(String username) {
        String query = String.format("SELECT * FROM user WHERE username = '%s' LIMIT 1", username);
        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new IUser(name, email, username, password);
            } else {
                System.out.println("No user found");
                return new IUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new IUser();
        }
    }

    public void create(IUser user) throws SQLIntegrityConstraintViolationException {
        String name = user.getName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
    
        String userQuery = String.format(
                "INSERT INTO user (name, email, username, password) VALUES ('%s', '%s', '%s', '%s')",
                name, email, username, password);
    
        try {
            Statement stmt = Server.conn.createStatement();
            stmt.executeUpdate(userQuery, Statement.RETURN_GENERATED_KEYS);
    
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long userId = rs.getLong(1);

                String rosterQuery = String.format("INSERT INTO user_roster (user_id) VALUES ('%d')", userId);
                stmt.executeUpdate(rosterQuery);
    
                System.out.println("User and roster created successfully. User ID: " + userId);
            } else {
                System.out.println("Error generating user ID");
            }
    
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}