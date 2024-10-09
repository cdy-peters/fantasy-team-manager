package com.example.client.controllers;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.example.client.Client;
import com.example.client.helpers.HttpHelper;
import com.example.client.helpers.LandingGuard;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller for the login page.
 */
public class LoginController {
    @FXML
    private Hyperlink createAccountLink;
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
    public LoginController() {
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
     * Handle an error response from the server.
     * 
     * @param response
     */
    private void handleError(HttpResponse<String> response) {
        Integer code = response.statusCode();
        switch (code) {
            case 400:
                submitError.setText("Please fill in all fields");
                break;
            case 401:
                submitError.setText("Incorrect credentials");
                break;
            default:
                submitError.setText("An error occurred");
                System.out.println(response.body());
                break;
        }
    }

    /**
     * Redirect the user to the registration page.
     * 
     * @throws IOException If an error occurs while redirecting the user.
     */
    @FXML
    protected void onCreateAccountLinkClick() throws IOException {
        Client.updateRoot("/register-view.fxml");
    }

    /**
     * Submit the login form.
     * Send a request to the server to authenticate the user.
     * If successful, redirect the user to the landing page.
     */
    @FXML
    protected void onSubmitButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String body = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        HttpHelper request = new HttpHelper("login", body);

        try {
            HttpResponse<String> response = request.send();
            boolean isSuccessful = handleResponse(response);

            if (!isSuccessful) {
                return;
            }

            String view = new LandingGuard().getView();
            if (view.equals("/login-view.fxml")) {
                throw new IOException("Failed to fetch view");
            }

            Client.updateRoot(view);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
