package com.example.client.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class NavbarController {

    @FXML
    private Button signOutButton;

    @FXML
    protected void onSignOutButtonClick() throws IOException {
        String sessionCookie = PrefsHelper.getPref("sessionCookie");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "logout"))
                .header("Cookie", sessionCookie)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode != 200 && statusCode != 205) {
                System.out.println("An error occurred: " + response.body());
                return;
            }

            PrefsHelper.removePref("sessionCookie");

            Stage stage = (Stage) signOutButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }
}
