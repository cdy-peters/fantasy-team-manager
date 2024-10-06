package com.example.client.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.example.client.Client;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    @FXML
    private Text submitError;
    @FXML
    private Button submitButton;

    private HttpClient httpClient = HttpClient.newHttpClient();

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private HttpRequest createHttpRequest(String body) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    private void handleError(HttpResponse<String> response) {
        int code = response.statusCode();

        switch (code) {
            case 400:
                submitError.setText("Please fill in all fields");
                break;
            case 422:
                submitError.setText("Invalid credentials");
                break;
            case 409:
                submitError.setText("Account already exists");
                break;
            default:
                submitError.setText("An error occurred");
                break;
        }

    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private boolean handleResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() != 201) {
            handleError(response);
            return false;
        }

        Map<String, List<String>> headers = response.headers().map();
        List<String> cookies = headers.get("Set-Cookie");

        String sessionCookie = cookies.get(0);
        PrefsHelper.setPref("sessionCookie", sessionCookie);
        Client.sessionCookie = sessionCookie;
        return true;
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

        HttpRequest request = createHttpRequest(body);

        try {
            HttpResponse<String> response = sendRequest(request);
            boolean isSuccessful = handleResponse(response);

            if (!isSuccessful) {
                return;
            }

            Stage stage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/create-roster-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
            stage.setScene(scene);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
