package com.example.server;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User findByUsername(String username) {
        String query = String.format("SELECT * FROM user WHERE username = '%s' LIMIT 1", username);
        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new User(name, email, username, password);
            } else {
                System.out.println("No user found");
                return new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new User();
        }
    }

    public void create(User user) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}