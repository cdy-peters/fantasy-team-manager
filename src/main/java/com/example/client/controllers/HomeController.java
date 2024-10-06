package com.example.client.controllers;

import java.util.List;

import com.example.client.Client;
import com.example.server.models.IStatistics;
import com.example.server.models.IUserRoster;
import com.example.server.models.PlayerStatisticsDAO;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

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

    private void createPlayerCard(HBox hBox, IStatistics player) {
        VBox vBox = new VBox();
        vBox.setSpacing(2.0);
        vBox.setStyle("-fx-border-color: black; -fx-border-width: 0.7; -fx-border-radius: 2;");
        vBox.setPadding(new Insets(2, 5, 2, 5));

        Text name = new Text(player.getPlayerName());
        name.setStrokeWidth(0.0);
        vBox.getChildren().add(name);
        
        Text score = new Text("Score: " + (int) Math.rint(player.getPlayerScore()));
        score.setStrokeWidth(0.0);
        vBox.getChildren().add(score);

        hBox.getChildren().add(vBox);
    }

    private IStatistics getPlayer(Long playerId) {
        List<IStatistics> player = PlayerStatisticsDAO.findByPlayerId(String.valueOf(playerId));
        return player.get(0);
    }

    public void initialize() {
        roster = Client.userRoster;

        // Forwards
        createPlayerCard(forwards, getPlayer(roster.getPosition1()));
        createPlayerCard(forwards, getPlayer(roster.getPosition2()));

        // Midfielders
        createPlayerCard(midfielders, getPlayer(roster.getPosition3()));
        createPlayerCard(midfielders, getPlayer(roster.getPosition4()));
        createPlayerCard(midfielders, getPlayer(roster.getPosition5()));
        createPlayerCard(midfielders, getPlayer(roster.getPosition6()));

        // Defenders
        createPlayerCard(defenders, getPlayer(roster.getPosition7()));
        createPlayerCard(defenders, getPlayer(roster.getPosition8()));
        createPlayerCard(defenders, getPlayer(roster.getPosition9()));
        createPlayerCard(defenders, getPlayer(roster.getPosition10()));

        // Goalkeeper
        createPlayerCard(goalkeeper, getPlayer(roster.getPosition11()));
    }
}
