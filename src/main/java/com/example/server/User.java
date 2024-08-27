package com.example.server;

import java.util.regex.*;

public class User {
    private String name;
    private String email;
    private String username;
    private String password;

    public User() {
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validation
    public static Boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\s]{3,255}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static Boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static Boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,255}$");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public static Boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,255}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
