package com.example.client.controllers;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.server.models.IUser;
import com.example.server.models.IUserRoster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URI;

public class LeaderBoardController {
    // @FXML
    // private TableView<Player> leaderboardTable;
    @FXML
    private TableColumn<IUser, String> nameColumn;

    @FXML
    private TableColumn<IUserRoster, Integer> scoreColumn;

    private HttpClient httpClient = HttpClient.newHttpClient();

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private HttpRequest createHttpRequest(String position) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "/roster"))
                .header("Content-Type", "application/json")
                .build();
    }

    private HttpResponse <String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
