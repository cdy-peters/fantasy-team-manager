package com.example.client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import io.github.cdimascio.dotenv.Dotenv;

public class Client extends Application {
    public static Dotenv dotenv = Dotenv.load();
    public static final String SERVER_URL = dotenv.get("SERVER_URL");

    public static final String TITLE = "Fantasy Team Manager";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
