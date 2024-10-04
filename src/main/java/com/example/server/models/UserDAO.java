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
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new IUser(id, name, email, username, password);
            } else {
                System.out.println("No user found");
                return new IUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new IUser();
        }
    }

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
            stmt.executeUpdate(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new Exception("Error creating user");
    }

}