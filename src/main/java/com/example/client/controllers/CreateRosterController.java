package com.example.client.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.client.Client;
import com.example.server.models.IPlayer;
import com.example.server.models.IUserRoster;
import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

public class CreateRosterController {
    @FXML
    private ComboBox<IPlayer> LS;
    @FXML
    private ComboBox<IPlayer> RS;
    @FXML
    private ComboBox<IPlayer> LW;
    @FXML
    private ComboBox<IPlayer> LM;
    @FXML
    private ComboBox<IPlayer> RM;
    @FXML
    private ComboBox<IPlayer> RW;
    @FXML
    private ComboBox<IPlayer> LWB;
    @FXML
    private ComboBox<IPlayer> LB;
    @FXML
    private ComboBox<IPlayer> RB;
    @FXML
    private ComboBox<IPlayer> RWB;
    @FXML
    private ComboBox<IPlayer> GK;
    @FXML
    private Button submitButton;

    Gson g = new Gson();

    private HttpClient httpClient = HttpClient.newHttpClient();

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private HttpRequest createHttpRequest(String position) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "players?position=" + position))
                .header("Content-Type", "application/json")
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private class PlayerListCell extends ListCell<IPlayer> {
        @Override
        protected void updateItem(IPlayer player, boolean empty) {
            super.updateItem(player, empty);

            if (player == null || empty) {
                setText(null);
            } else {
                setText(player.getName());
            }
        }
    }

    private void initComboBox(ComboBox<IPlayer> comboBox, ObservableList<IPlayer> players) {
        comboBox.getItems().addAll(players);
        comboBox.setCellFactory(param -> new PlayerListCell());
        comboBox.setButtonCell(new PlayerListCell());
    }

    private ObservableList<IPlayer> fetchPlayers(String position) {
        try {
            HttpRequest request = createHttpRequest(position);
            HttpResponse<String> response = sendRequest(request);

            IPlayer[] players = g.fromJson(response.body(), IPlayer[].class);
            return FXCollections.observableArrayList(players);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public void initialize() {
        ObservableList<IPlayer> forwards = fetchPlayers("FW");
        ObservableList<IPlayer> midfielders = fetchPlayers("MF");
        ObservableList<IPlayer> defenders = fetchPlayers("DF");
        ObservableList<IPlayer> goalkeepers = fetchPlayers("GK");

        initComboBox(LS, forwards);
        initComboBox(RS, forwards);

        initComboBox(LW, midfielders);
        initComboBox(LM, midfielders);
        initComboBox(RM, midfielders);
        initComboBox(RW, midfielders);

        initComboBox(LWB, defenders);
        initComboBox(LB, defenders);
        initComboBox(RB, defenders);
        initComboBox(RWB, defenders);

        initComboBox(GK, goalkeepers);
    }

    // TODO: Handle duplicate selected players
    // TODO: Handle no player selected
    @FXML
    protected void onSubmitButtonClick() {
        IPlayer LSPlayer = LS.getValue();
        IPlayer RSPlayer = RS.getValue();
        IPlayer LWPlayer = LW.getValue();
        IPlayer LMPlayer = LM.getValue();
        IPlayer RMPlayer = RM.getValue();
        IPlayer RWPlayer = RW.getValue();
        IPlayer LWBPlayer = LWB.getValue();
        IPlayer LBPlayer = LB.getValue();
        IPlayer RBPlayer = RB.getValue();
        IPlayer RWBPlayer = RWB.getValue();
        IPlayer GKPlayer = GK.getValue();

        IUserRoster userRoster = new IUserRoster(LSPlayer.getId(), RSPlayer.getId(), LWPlayer.getId(),
                LMPlayer.getId(),
                RMPlayer.getId(), RWPlayer.getId(), LWBPlayer.getId(), LBPlayer.getId(), RBPlayer.getId(),
                RWBPlayer.getId(), GKPlayer.getId(), null, null, null, null);

        double scoreSum = 0;
        scoreSum += LSPlayer.getScore();
        scoreSum += RSPlayer.getScore();
        scoreSum += LWPlayer.getScore();
        scoreSum += LMPlayer.getScore();
        scoreSum += RMPlayer.getScore();
        scoreSum += RWPlayer.getScore();
        scoreSum += LWBPlayer.getScore();
        scoreSum += LBPlayer.getScore();
        scoreSum += RBPlayer.getScore();
        scoreSum += RWBPlayer.getScore();
        scoreSum += GKPlayer.getScore();

        userRoster.setScore(scoreSum);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Client.SERVER_URL + "roster"))
                    .header("Cookie", Client.sessionCookie)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(g.toJson(userRoster)))
                    .build();

            HttpResponse<String> response = sendRequest(request);
            System.out.println(response.body());

            Client.userRoster = userRoster;

            Stage stage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Client.WIDTH, Client.HEIGHT);
            stage.setScene(scene);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
