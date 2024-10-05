package com.example.client.controllers;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.server.models.ILeaderboardElement;
import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URI;

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
    private HttpClient httpClient;
    private Gson gson;

    public LeaderBoardController() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @FXML
    public void initialize() {
        data = FXCollections.observableArrayList();

        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        data.addAll(fetchPlayers());
        leaderboardTable.setItems(data);
    }

    private ObservableList<ILeaderboardElement> fetchPlayers() {
        try {
            HttpRequest request = createHttpRequest();
            HttpResponse<String> response = sendRequest(request);

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

    private HttpRequest createHttpRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "rosters"))
                .header("Content-Type", "application/json")
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
