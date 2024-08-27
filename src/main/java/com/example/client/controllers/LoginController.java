package com.example.client.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Hyperlink createAccountLink;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private void handleError(HttpResponse<String> response) {
        if (response.statusCode() == 401) {
            System.out.println(response.body());
        } else if (response.statusCode() == 400) {
            System.out.println(response.body());
        } else {
            System.out.println("An error occurred");
        }
    }

    @FXML
    protected void onCreateAccountLinkClick() throws IOException {
        Stage stage = (Stage) createAccountLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSubmitButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String body = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                handleError(response);
                return;
            }

            String cookie = response.headers().firstValue("Set-Cookie").get();
            System.out.println("Logged in " + cookie);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
