package com.example.client.controllers;

import java.io.IOException;

import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.client.helpers.HttpHelper;
import com.example.client.helpers.LandingGuard;
import com.example.client.helpers.PrefsHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the navbar.
 */
public class NavbarController {
    @FXML
    private Button signOutButton;
    @FXML
    private Button rosterButton;

    /**
     * Default constructor
     */
    public NavbarController() {
    }

    /**
     * Handle the sign out button click event.
     * Send a request to the server to log out the user.
     * If the request is successful, remove the session token from the user's
     * preferences.
     */
    @FXML
    protected void onSignOutButtonClick() {

        HttpHelper request = new HttpHelper("logout", "");

        try {
            HttpResponse<String> response = request.send();

            int statusCode = response.statusCode();
            if (statusCode != 200 && statusCode != 205) {
                System.out.println("An error occurred: " + response.body());
                return;
            }

            PrefsHelper.removePref("sessionToken");
            Client.userRoster = null;

            Client.updateRoot("/login-view.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Handle the roster button click event.
     * Redirect the user to the roster page.
     * 
     * @throws IOException If an error occurs while fetching the view.
     */
    @FXML
    protected void onRosterButtonClick() throws IOException {
        String view = new LandingGuard().getView();
        if (view.equals("/login-view.fxml") || view.equals("/register-view.fxml")) {
            throw new IOException("Error fetching view");
        }

        Client.updateRoot(view);
    }

    /**
     * Handle the home button click event.
     * Redirect the user to the home page.
     */
    @FXML
    protected void onLeaderBoardButtonClick() {
        Client.updateRoot("/leaderboard-view.fxml");
    }

    /**
     * Handle the statistics button click event.
     * Redirect the user to the statistics page.
     */
    @FXML
    protected void onStatisticsButtonClick() {
        Client.updateRoot("/statistics-view.fxml");
    }
}
