package com.example.server;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Server {
    public static Dotenv dotenv = Dotenv.load();
    public static Connection conn = DbConnection.getConnection();

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
