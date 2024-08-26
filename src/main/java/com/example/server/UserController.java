package com.example.server;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(username);
        System.out.println(password);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        String name = user.getName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(name);
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
    }

}
