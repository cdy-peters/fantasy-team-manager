package com.example.server.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IUser;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private SessionDAO sessionDAO = new SessionDAO();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private ResponseEntity<?> createSession(HttpServletRequest request, Long userId) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", userId);

        try {
            sessionDAO.create(session);
            String cookie = "SESSION=" + session.getId();
            return ResponseEntity.status(201).header("Set-Cookie", cookie).body("Session created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create session");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody IUser user) {
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

        return createSession(request, existingUser.getId());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(HttpServletRequest request, @RequestBody IUser user) {
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
            return createSession(request, userId);
        } catch (SQLIntegrityConstraintViolationException e) {
            return ResponseEntity.status(409).body("Username or email already exists");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create user");
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(205).body("Session not found");
        }

        try {
            sessionDAO.delete(session.getId());
            session.invalidate();

            return ResponseEntity.status(200).body("Logged out");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to logout");
        }
    }

}
