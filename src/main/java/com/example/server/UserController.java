package com.example.server;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (username.isEmpty()) {
            return ResponseEntity.status(400).body("Missing username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(400).body("Missing password");
        }

        User existingUser = userDAO.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.status(401).body("Incorrect credentials");
        }

        Boolean passwordMatch = passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        if (!passwordMatch) {
            return ResponseEntity.status(401).body("Incorrect credentials");
        }

        return ResponseEntity.status(200).body(existingUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String name = user.getName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        if (name.isEmpty()) {
            return ResponseEntity.status(400).body("Missing name");
        }
        if (!User.isNameValid(name)) {
            return ResponseEntity.status(422).body("Invalid name");
        }

        if (email.isEmpty()) {
            return ResponseEntity.status(400).body("Missing email");
        }
        if (!User.isEmailValid(email)) {
            return ResponseEntity.status(422).body("Invalid email");
        }

        if (username.isEmpty()) {
            return ResponseEntity.status(400).body("Missing username");
        }
        if (!User.isUsernameValid(username)) {
            return ResponseEntity.status(422).body("Invalid username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(400).body("Missing password");
        }
        if (!User.isPasswordValid(password)) {
            return ResponseEntity.status(422).body("Invalid password");
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        try {
            userDAO.create(user);
            return ResponseEntity.status(201).body(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            return ResponseEntity.status(409).body("Username or email already exists");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }

}
