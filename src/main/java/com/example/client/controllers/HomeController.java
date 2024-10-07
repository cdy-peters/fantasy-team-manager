package com.example.client.controllers;

import java.util.List;

import com.example.client.Client;
import com.example.server.models.IStatistics;
import com.example.server.models.IUserRoster;
import com.example.server.models.PlayerStatisticsDAO;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

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

    private IStatistics getPlayer(Long playerId) {
        List<IStatistics> player = PlayerStatisticsDAO.findByPlayerId(String.valueOf(playerId));
        return player.get(0);
    }

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
