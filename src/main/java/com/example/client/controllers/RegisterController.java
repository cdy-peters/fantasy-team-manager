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

public class RegisterController {
    @FXML
    private Hyperlink signInLink;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private void handleError(HttpResponse<String> response) {
        if (response.statusCode() == 400) {
            System.out.println(response.body());
        } else if (response.statusCode() == 422) {
            System.out.println(response.body());
        } else if (response.statusCode() == 409) {
            System.out.println(response.body());
        } else {
            System.out.println("An error occurred");
        }
    }

    @FXML
    protected void onSignInLinkClick() throws IOException {
        Stage stage = (Stage) signInLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSubmitButtonClick() {
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        String body = String.format(
                "{\"name\": \"%s\", \"email\": \"%s\", \"username\": \"%s\", \"password\": \"%s\"}",
                name, email, username, password);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "register"))
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
            System.out.println("User created " + cookie);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
