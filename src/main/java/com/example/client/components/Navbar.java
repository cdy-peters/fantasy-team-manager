package com.example.client.components;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.client.helpers.LandingGuard;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Navbar extends HBox {
    @FXML
    private Button signOutButton;

    @FXML
    private Button rosterButton;

    @FXML
    private Button leaderBoardButton;

    public Navbar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/navbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to load Navbar FXML", exception);
        }

    }

    @FXML
    protected void onSignOutButtonClick() throws IOException {
        String sessionToken = PrefsHelper.getPref("sessionToken");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "logout"))
                .header("Authorization", sessionToken)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode != 200 && statusCode != 205) {
                System.out.println("An error occurred: " + response.body());
                return;
            }

            PrefsHelper.removePref("sessionToken");
            Client.userRoster = null;

            Stage stage = (Stage) signOutButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    @FXML
    protected void onRosterButtonClick() throws IOException {
        String view = new LandingGuard().getView();
        if (view.equals("/login-view.fxml") || view.equals("/register-view.fxml")) {
            throw new IOException("Error fetching view");
        }

        Stage stage = (Stage) rosterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onLeaderBoardButtonClick() throws IOException {
        Stage stage = (Stage) leaderBoardButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/leaderboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
        stage.setScene(scene);
    }
}
