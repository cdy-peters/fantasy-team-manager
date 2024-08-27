package com.example.client;

import java.io.IOException;

import com.example.client.helpers.PrefsHelper;

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

    // TODO: Handle expired session cookie
    public static String sessionCookie = PrefsHelper.getPref("sessionCookie");

    @Override
    public void start(Stage stage) throws IOException {
        String view = sessionCookie.isEmpty() ? "/login-view.fxml" : "/home-view.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
