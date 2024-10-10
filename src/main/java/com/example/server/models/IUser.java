package com.example.server.models;

import java.util.regex.*;

/**
 * Interface for the user.
 */
public class IUser {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;

    /**
     * Create a new IUser instance.
     */
    public IUser() {
    }

    /**
     * Create a new IUser instance.
     * 
     * @param name     The name
     * @param email    The email
     * @param username The username
     * @param password The password
     */
    public IUser(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Create a new IUser instance.
     * 
     * @param id       The ID
     * @param name     The name
     * @param email    The email
     * @param username The username
     * @param password The password
     */
    public IUser(Long id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters
    /**
     * Get the ID of the user.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the user.
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the email of the user.
     * 
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the username of the user.
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the password of the user.
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    // Setters
    /**
     * Set the ID of the user.
     * 
     * @param id The ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the name of the user.
     * 
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the email of the user.
     * 
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the username of the user.
     * 
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the password of the user.
     * 
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // Validation
    /**
     * Check if the name is valid.
     * 
     * @param name The name
     * @return Boolean
     */
    public static Boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\s]{3,255}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    /**
     * Check if the email is valid.
     * 
     * @param email The email
     * @return Boolean
     */
    public static Boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    /**
     * Check if the username is valid.
     * 
     * @param username The username
     * @return Boolean
     */
    public static Boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]{3,20}$");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    /**
     * Check if the password is valid.
     * 
     * @param password The password
     * @return Boolean
     */
    public static Boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,255}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
