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

public class LeaderBoardController {
    @FXML
    private TableView<ILeaderboardElement> leaderboardTable;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> rankColumn;

    @FXML
    private TableColumn<ILeaderboardElement, String> playerColumn;

    @FXML
    private TableColumn<ILeaderboardElement, Integer> scoreColumn;

    private ObservableList<ILeaderboardElement> data;
    private Gson gson;

    public LeaderBoardController() {
        this.gson = new Gson();
    }

    @FXML
    public void initialize() {
        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        data = FXCollections.observableArrayList();

        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        data.addAll(fetchPlayers());
        leaderboardTable.setItems(data);
    }

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
}
