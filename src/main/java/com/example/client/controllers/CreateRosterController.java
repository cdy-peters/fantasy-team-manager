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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreateRosterController {
    @FXML
    private Button playerCard1;
    @FXML
    private Button playerCard2;
    @FXML
    private Button playerCard3;
    @FXML
    private Button playerCard4;
    @FXML
    private Button playerCard5;
    @FXML
    private Button playerCard6;
    @FXML
    private Button playerCard7;
    @FXML
    private Button playerCard8;
    @FXML
    private Button playerCard9;
    @FXML
    private Button playerCard10;
    @FXML
    private Button playerCard11;
    @FXML
    private TextField searchPlayersField;
    @FXML
    private ListView<IPlayer> playersList;
    @FXML
    private Button createTeamButton;

    private Button selectedPlayerCard = null;

    private IUserRoster roster = new IUserRoster();

    private ObservableList<IPlayer> forwards;
    private ObservableList<IPlayer> midfielders;
    private ObservableList<IPlayer> defenders;
    private ObservableList<IPlayer> goalkeepers;

    private FilteredList<IPlayer> filteredPlayers;

    Gson g = new Gson();
    private HttpClient httpClient = HttpClient.newHttpClient();

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

    private HttpRequest createHttpRequest(String position) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "players?position=" + position))
                .header("Content-Type", "application/json")
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
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
        forwards = fetchPlayers("FW");
        midfielders = fetchPlayers("MF");
        defenders = fetchPlayers("DF");
        goalkeepers = fetchPlayers("GK");

        playerCard1.setOnAction(this::handlePlayerCard);
        playerCard2.setOnAction(this::handlePlayerCard);
        playerCard3.setOnAction(this::handlePlayerCard);
        playerCard4.setOnAction(this::handlePlayerCard);
        playerCard5.setOnAction(this::handlePlayerCard);
        playerCard6.setOnAction(this::handlePlayerCard);
        playerCard7.setOnAction(this::handlePlayerCard);
        playerCard8.setOnAction(this::handlePlayerCard);
        playerCard9.setOnAction(this::handlePlayerCard);
        playerCard10.setOnAction(this::handlePlayerCard);
        playerCard11.setOnAction(this::handlePlayerCard);

        playersList.setOnMouseClicked(this::handlePlayerSelection);

        createTeamButton.setOnAction(this::handleCreateTeam);
    }

    private void handlePlayerCard(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (selectedPlayerCard != null) {
            selectedPlayerCard.getStyleClass().remove("player-card-selected");
        }
        selectedPlayerCard = button;
        selectedPlayerCard.getStyleClass().add("player-card-selected");

        switch (button.getId()) {
            case "playerCard1":
                updatePlayerList(forwards);
                break;
            case "playerCard2":
                updatePlayerList(forwards);
                break;
            case "playerCard3":
                updatePlayerList(midfielders);
                break;
            case "playerCard4":
                updatePlayerList(midfielders);
                break;
            case "playerCard5":
                updatePlayerList(midfielders);
                break;
            case "playerCard6":
                updatePlayerList(midfielders);
                break;
            case "playerCard7":
                updatePlayerList(defenders);
                break;
            case "playerCard8":
                updatePlayerList(defenders);
                break;
            case "playerCard9":
                updatePlayerList(defenders);
                break;
            case "playerCard10":
                updatePlayerList(defenders);
                break;
            case "playerCard11":
                updatePlayerList(goalkeepers);
                break;
            default:
                break;
        }
    }

    private void updatePlayerList(ObservableList<IPlayer> players) {
        filteredPlayers = new FilteredList<>(players, p -> true);
        playersList.setItems(filteredPlayers);
        playersList.setCellFactory(param -> new PlayerListCell());

        searchPlayersField.textProperty().addListener(obs -> {
            String filter = searchPlayersField.getText();
            if (filter == null || filter.length() == 0) {
                filteredPlayers.setPredicate(p -> true);
            } else {
                filteredPlayers.setPredicate(p -> p.getName().toLowerCase().contains(filter.toLowerCase()));
            }
        });
    }

    private void handlePlayerSelection(MouseEvent event) {
        IPlayer selectedPlayer = playersList.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            VBox vBox = new VBox();
            vBox.setAlignment(javafx.geometry.Pos.CENTER);

            Text text = new Text(selectedPlayer.getName());
            vBox.getChildren().add(text);

            selectedPlayerCard.setGraphic(vBox);

            switch (selectedPlayerCard.getId()) {
                case "playerCard1":
                    roster.setPosition1(selectedPlayer.getId());
                    break;
                case "playerCard2":
                    roster.setPosition2(selectedPlayer.getId());
                    break;
                case "playerCard3":
                    roster.setPosition3(selectedPlayer.getId());
                    break;
                case "playerCard4":
                    roster.setPosition4(selectedPlayer.getId());
                    break;
                case "playerCard5":
                    roster.setPosition5(selectedPlayer.getId());
                    break;
                case "playerCard6":
                    roster.setPosition6(selectedPlayer.getId());
                    break;
                case "playerCard7":
                    roster.setPosition7(selectedPlayer.getId());
                    break;
                case "playerCard8":
                    roster.setPosition8(selectedPlayer.getId());
                    break;
                case "playerCard9":
                    roster.setPosition9(selectedPlayer.getId());
                    break;
                case "playerCard10":
                    roster.setPosition10(selectedPlayer.getId());
                    break;
                case "playerCard11":
                    roster.setPosition11(selectedPlayer.getId());
                    break;
                default:
                    break;
            }
            roster.incScore(selectedPlayer.getScore());
        }
    }

    private void handleCreateTeam(ActionEvent event) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Client.SERVER_URL + "roster"))
                    .header("Authorization", Client.sessionToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(g.toJson(roster)))
                    .build();

            HttpResponse<String> response = sendRequest(request);
            System.out.println(response.body());

            Client.userRoster = roster;

            Client.updateRoot("/home-view.fxml");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
