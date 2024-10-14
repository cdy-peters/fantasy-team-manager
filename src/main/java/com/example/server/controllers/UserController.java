package com.example.server.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.ISession;
import com.example.server.models.IUser;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;

/**
 * Controller for the user endpoints.
 */
@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private SessionDAO sessionDAO = new SessionDAO();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Default constructor
     */
    public UserController() {
    }

    /**
     * Create a session for the user
     * 
     * @param userId The user's ID
     * @return The response entity
     */
    protected ResponseEntity<?> createSession(Long userId) {
        try {
            ISession session = sessionDAO.create(userId);
            String token = session.getSessionId();
            return ResponseEntity.status(201).header("Authorization", token).body("Session created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create session");
        }
    }

    /**
     * Authenticate the user
     * 
     * @param user The user object
     * @return The response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody IUser user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // Validation
        if (username.isEmpty()) {
            return ResponseEntity.status(400).body("Missing username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(400).body("Missing password");
        }

        // Check if user exists
        IUser existingUser = userDAO.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.status(401).body("Incorrect credentials");
        }

        // Check if password matches
        Boolean passwordMatch = passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        if (!passwordMatch) {
            return ResponseEntity.status(401).body("Incorrect credentials");
        }

        return createSession(existingUser.getId());
    }

    /**
     * Register a new user
     * 
     * @param user The user object
     * @return The response entity
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody IUser user) {
        String name = user.getName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        // Validation
        if (name.isEmpty()) {
            return ResponseEntity.status(400).body("Missing name");
        }
        if (!IUser.isNameValid(name)) {
            return ResponseEntity.status(422).body("Invalid name");
        }

        if (email.isEmpty()) {
            return ResponseEntity.status(400).body("Missing email");
        }
        if (!IUser.isEmailValid(email)) {
            return ResponseEntity.status(422).body("Invalid email");
        }

        if (username.isEmpty()) {
            return ResponseEntity.status(400).body("Missing username");
        }
        if (!IUser.isUsernameValid(username)) {
            return ResponseEntity.status(422).body("Invalid username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(400).body("Missing password");
        }
        if (!IUser.isPasswordValid(password)) {
            return ResponseEntity.status(422).body("Invalid password");
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        // Create user
        try {
            Long userId = userDAO.create(user);
            return createSession(userId);
        } catch (SQLIntegrityConstraintViolationException e) {
            return ResponseEntity.status(409).body("Username or email already exists");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create user");
        }

    }

    /**
     * Logout the user
     * 
     * @param token The session token
     * @return The response entity
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization", required = true) String token) {
        ISession session = sessionDAO.find(token);
        if (session == null) {
            return ResponseEntity.status(205).body("Session not found");
        }

        try {
            sessionDAO.delete(session.getSessionId());
            return ResponseEntity.status(200).body("Logged out");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to logout");
        }
    }

}
