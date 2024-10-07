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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CreateRosterController {
    @FXML
    private HBox forwardsHBox;
    @FXML
    private HBox midfieldersHBox;
    @FXML
    private HBox defendersHBox;
    @FXML
    private HBox goalkeeperHBox;
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
    private PlayerCardController playerCardController = new PlayerCardController();

    private class PlayerListCell extends ListCell<IPlayer> {
        @Override
        protected void updateItem(IPlayer player, boolean empty) {
            super.updateItem(player, empty);

            if (player == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(player.getName());

                String filePath = "/images/badges/" + player.getTeam() + ".png";
                Image image = new Image(getClass().getResourceAsStream(filePath));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(20);
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
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

        playerCardController.create(forwardsHBox, "FW", 1, this::handlePlayerCard);
        playerCardController.create(forwardsHBox, "FW", 2, this::handlePlayerCard);
        playerCardController.create(midfieldersHBox, "MF", 3, this::handlePlayerCard);
        playerCardController.create(midfieldersHBox, "MF", 4, this::handlePlayerCard);
        playerCardController.create(midfieldersHBox, "MF", 5, this::handlePlayerCard);
        playerCardController.create(midfieldersHBox, "MF", 6, this::handlePlayerCard);
        playerCardController.create(defendersHBox, "DF", 7, this::handlePlayerCard);
        playerCardController.create(defendersHBox, "DF", 8, this::handlePlayerCard);
        playerCardController.create(defendersHBox, "DF", 9, this::handlePlayerCard);
        playerCardController.create(defendersHBox, "DF", 10, this::handlePlayerCard);
        playerCardController.create(goalkeeperHBox, "GK", 11, this::handlePlayerCard);

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
            case "playerCard2":
                updatePlayerList(forwards);
                break;
            case "playerCard3":
            case "playerCard4":
            case "playerCard5":
            case "playerCard6":
                updatePlayerList(midfielders);
                break;
            case "playerCard7":
            case "playerCard8":
            case "playerCard9":
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
            playerCardController.update(selectedPlayerCard, selectedPlayer);

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
