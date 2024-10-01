package com.example.client.controllers;

import com.example.server.models.IPlayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class HomeController {
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

    public void initialize() {
        ObservableList<IPlayer> forwards = FXCollections.observableArrayList();
        ObservableList<IPlayer> midfielders = FXCollections.observableArrayList();
        ObservableList<IPlayer> defenders = FXCollections.observableArrayList();
        ObservableList<IPlayer> goalkeepers = FXCollections.observableArrayList();

        forwards.addAll(
                new IPlayer(1, "Cristiano Ronaldo"),
                new IPlayer(2, "Lionel Messi"),
                new IPlayer(3, "Robert Lewandowski"),
                new IPlayer(4, "Harry Kane"),
                new IPlayer(5, "Erling Haaland"));
        midfielders.addAll(
                new IPlayer(6, "Kevin De Bruyne"),
                new IPlayer(7, "N'Golo Kanté"),
                new IPlayer(8, "Bruno Fernandes"),
                new IPlayer(9, "Joshua Kimmich"),
                new IPlayer(10, "Luka Modrić"));
        defenders.addAll(
                new IPlayer(11, "Virgil van Dijk"),
                new IPlayer(12, "Ruben Dias"),
                new IPlayer(13, "Andrew Robertson"),
                new IPlayer(14, "João Cancelo"),
                new IPlayer(15, "Trent Alexander-Arnold"));
        goalkeepers.addAll(
                new IPlayer(16, "Jan Oblak"),
                new IPlayer(17, "Alisson Becker"),
                new IPlayer(18, "Ederson"),
                new IPlayer(19, "Thibaut Courtois"),
                new IPlayer(20, "Marc-André ter Stegen"));

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

        // TODO: Create team query
    }
}
