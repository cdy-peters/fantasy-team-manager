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

/**
 * Entry point for the client application.
 */
public class Client extends Application {
    /** Title of the window */
    public static final String TITLE = "Fantasy Team Manager";
    /** Width of the window */
    public static final int WIDTH = 900;
    /** Height of the window */
    public static final int HEIGHT = 650;

    // TODO: Handle expired session cookie
    /** The user's session token */
    public static String sessionToken = PrefsHelper.getPref("sessionToken");

    /** The user's roster */
    public static IUserRoster userRoster = null;

    /** The root layout */
    public static BorderPane root = new BorderPane();

    /**
     * Default constructor
     */
    public Client() {
    }

    /**
     * Update the root layout with the specified view.
     * Conditionally displays a navbar based on whether the user is logged in.
     * 
     * @param view The path to the view file.
     */
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

    /**
     * Start the application.
     * 
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the view.
     */
    @Override
    public void start(Stage stage) throws IOException {
        String view = new LandingGuard().getView();
        updateRoot(view);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launch the application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
