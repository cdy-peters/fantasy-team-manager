package com.example.client.controllers;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.example.client.Client;
import com.example.client.helpers.HttpHelper;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller for the register page.
 */
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

    /**
     * Default constructor
     */
    public RegisterController() {
    }

    /**
     * Handle an error response from the server.
     * 
     * @param response
     */
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
                submitError.setText("An error occurred: " + response.body());
                break;
        }

    }

    /**
     * Handle the response from the server.
     * 
     * @param response
     * @return True if the response was successful, false otherwise.
     * @throws IOException If an error occurs while handling the response.
     */
    private boolean handleResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() != 201) {
            handleError(response);
            return false;
        }

        Map<String, List<String>> headers = response.headers().map();
        List<String> authHeader = headers.get("Authorization");

        String sessionToken = authHeader.get(0);
        PrefsHelper.setPref("sessionToken", sessionToken);
        Client.sessionToken = sessionToken;

        return true;
    }

    /**
     * Handle the sign in link click event.
     * Redirect the user to the login page.
     * 
     * @throws IOException If an error occurs while fetching the view.
     */
    @FXML
    protected void onSignInLinkClick() throws IOException {
        Client.updateRoot("/login-view.fxml");
    }

    /**
     * Handle the submit button click event.
     * Send a request to the server to register the user.
     * If the request is successful, redirect the user to the create roster page.
     */
    @FXML
    protected void onSubmitButtonClick() {
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        String body = String.format(
                "{\"name\": \"%s\", \"email\": \"%s\", \"username\": \"%s\", \"password\": \"%s\"}",
                name, email, username, password);
        HttpHelper request = new HttpHelper("register", body);

        try {
            HttpResponse<String> response = request.send();
            boolean isSuccessful = handleResponse(response);

            if (!isSuccessful) {
                return;
            }

            Client.updateRoot("/create-roster-view.fxml");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
