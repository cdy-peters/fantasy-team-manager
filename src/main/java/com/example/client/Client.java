package com.example.client;

import java.io.IOException;

import com.example.client.helpers.LandingGuard;
import com.example.client.helpers.PrefsHelper;
import com.example.server.models.IUserRoster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import io.github.cdimascio.dotenv.Dotenv;

public class Client extends Application {
    public static Dotenv dotenv = Dotenv.load();
    public static final String SERVER_URL = dotenv.get("SERVER_URL");

    public static final String TITLE = "Fantasy Team Manager";
    public static final int WIDTH = 900;
    public static final int HEIGHT = 650;

    // TODO: Handle expired session cookie
    public static String sessionToken = PrefsHelper.getPref("sessionToken");

    public static IUserRoster userRoster = null;

    public static BorderPane root = new BorderPane();

    public static void updateRoot(String view) {
        if (view.equals("/login-view.fxml") || view.equals("/register-view.fxml")) {
            root.setTop(null);
        } else if (root.getTop() == null) {
            FXMLLoader navbarLoader = new FXMLLoader(Client.class.getResource("/navbar.fxml"));
            try {
                root.setTop(navbarLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FXMLLoader contentLoader = new FXMLLoader(Client.class.getResource(view));
        try {
            root.setCenter(contentLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        String view = new LandingGuard().getView();
        updateRoot(view);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
