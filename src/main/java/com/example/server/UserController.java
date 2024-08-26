package com.example.server;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        String username = user.getUsername();

        User existingUser = userDAO.findByUsername(username);
        System.out.println(existingUser.getEmail());
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userDAO.create(user);
    }

}
