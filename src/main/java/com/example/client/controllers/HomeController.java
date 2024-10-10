package com.example.client.controllers;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import com.example.client.Client;
import com.example.client.helpers.HttpHelper;
import com.example.server.models.IStatistics;
import com.example.server.models.IUserRoster;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 * Controller for the home page.
 */
public class HomeController {
    @FXML
    private HBox forwards;
    @FXML
    private HBox midfielders;
    @FXML
    private HBox defenders;
    @FXML
    private HBox goalkeeper;

    private IUserRoster roster;

    private PlayerCardController playerCardController = new PlayerCardController();

    private Gson gson = new Gson();

    /**
     * Default constructor
     */
    public HomeController() {
    }

    /**
     * Get player statistics from the server and handle the response
     * 
     * @param playerId The player's ID
     * @return The player's statistics
     */
    private IStatistics getPlayer(Long playerId) {
        String requestUrl = "player_statistics?playerId=" + playerId;
        HttpHelper request = new HttpHelper(requestUrl);

        try {
            HttpResponse<String> response = request.send();
            IStatistics[] player = gson.fromJson(response.body(), IStatistics[].class);
            return player[0];
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Initialize the home page.
     * Create player cards for each player in the user's roster.
     */
    public void initialize() {
        roster = Client.userRoster;

        playerCardController.create(forwards, getPlayer(roster.getPosition1()), 1);
        playerCardController.create(forwards, getPlayer(roster.getPosition2()), 2);
        playerCardController.create(midfielders, getPlayer(roster.getPosition3()), 3);
        playerCardController.create(midfielders, getPlayer(roster.getPosition4()), 4);
        playerCardController.create(midfielders, getPlayer(roster.getPosition5()), 5);
        playerCardController.create(midfielders, getPlayer(roster.getPosition6()), 6);
        playerCardController.create(defenders, getPlayer(roster.getPosition7()), 7);
        playerCardController.create(defenders, getPlayer(roster.getPosition8()), 8);
        playerCardController.create(defenders, getPlayer(roster.getPosition9()), 9);
        playerCardController.create(defenders, getPlayer(roster.getPosition10()), 10);
        playerCardController.create(goalkeeper, getPlayer(roster.getPosition11()), 11);
    }
}
