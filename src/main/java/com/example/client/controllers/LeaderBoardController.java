package com.example.client.controllers;

import java.net.http.HttpResponse;

import com.example.client.helpers.HttpHelper;
import com.example.server.models.ILeaderboardElement;
import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Controller for the leaderboard page.
 */
public class LeaderBoardController {
    @FXML
    private TableView<ILeaderboardElement> leaderboardTable;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> rankColumn;

    @FXML
    private TableColumn<ILeaderboardElement, String> userColumn;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> scoreColumn;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> costColumn;

    private ObservableList<ILeaderboardElement> leaderboardData;

    @FXML
    private TableView<ILeaderboardElement> currentUserTable;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> userRosterRank;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> userRosterScore;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> userRosterBudget;

    private ObservableList<ILeaderboardElement> currentUserData;

    @FXML
    private TableView<ILeaderboardElement> currentRosterTable;
    private ObservableList<ILeaderboardElement> currentRosterData;
    private Gson gson;

    /**
     * Constructor for the LeaderBoardController class.
     */
    public LeaderBoardController() {
        this.gson = new Gson();
    }

    /**
     * Initialize the leaderboard page, populating the table with player data.
     */
    @FXML
    public void initialize() {
        leaderboardData = FXCollections.observableArrayList();

        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        leaderboardData.addAll(fetchPlayers());
        leaderboardTable.setItems(leaderboardData);

        currentUserData = FXCollections.observableArrayList();

        userRosterRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        userRosterScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        userRosterRank.setCellValueFactory(new PropertyValueFactory<>("cost"));

        currentUserData.addAll(fetchRoster());
        currentUserTable.setItems(currentUserData);
        currentRosterData = FXCollections.observableArrayList();
    }

    /**
     * Fetch player data from the server.
     * 
     * @return An observable list of player data.
     */
    private ObservableList<ILeaderboardElement> fetchPlayers() {
        HttpHelper request = new HttpHelper("rosters");

        try {
            HttpResponse<String> response = request.send();

            ILeaderboardElement[] players = gson.fromJson(response.body(), ILeaderboardElement[].class);

            // debug
            System.out.println("Response: " + response.body());
            for (ILeaderboardElement player : players) {
                System.out.println("Player ID: " + player.getUserId() + ", Score: " + player.getScore());
            }
            // debug

            return FXCollections.observableArrayList(players);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    /**
     * Fetch player data from the server.
     * 
     * @return An observable list of player data.
     */
    private ObservableList<ILeaderboardElement> fetchRoster() {
        HttpHelper request = new HttpHelper("rosterStats");

        try {
            HttpResponse<String> response = request.send();

            ILeaderboardElement stats = gson.fromJson(response.body(), ILeaderboardElement.class);

            return FXCollections.observableArrayList(stats);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }
}
