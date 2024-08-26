package com.example.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        String username = user.getUsername();

        User existingUser = userDAO.findByUsername(username);

        Boolean passwordMatch = passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        if (passwordMatch) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        // Hash password
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        userDAO.create(user);
    }

}
