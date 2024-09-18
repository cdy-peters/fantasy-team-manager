package com.example.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class HomeController {
    @FXML
    private ComboBox<String> LS;
    @FXML
    private ComboBox<String> RS;
    @FXML
    private ComboBox<String> LW;
    @FXML
    private ComboBox<String> LM;
    @FXML
    private ComboBox<String> RM;
    @FXML
    private ComboBox<String> RW;
    @FXML
    private ComboBox<String> LWB;
    @FXML
    private ComboBox<String> LB;
    @FXML
    private ComboBox<String> RB;
    @FXML
    private ComboBox<String> RWB;
    @FXML
    private ComboBox<String> GK;

    public void initialize() {
        String[] strikers = {"Lionel Messi", "Cristiano Ronaldo", "Robert Lewandowski", "Kylian Mbappe", "Erling Haaland"};
        String[] midfielders = {"Kevin De Bruyne", "Luka Modric", "N'Golo Kante", "Bruno Fernandes", "Joshua Kimmich"};
        String[] defenders = {"Virgil van Dijk", "Sergio Ramos", "Trent Alexander-Arnold", "Andrew Robertson", "Kalidou Koulibaly"};
        String[] goalkeepers = {"Jan Oblak", "Alisson Becker", "Ederson", "Thibaut Courtois", "Marc-Andre ter Stegen"};

        LS.getItems().addAll(strikers);
        RS.getItems().addAll(strikers);

        LW.getItems().addAll(strikers);
        LM.getItems().addAll(midfielders);
        RM.getItems().addAll(midfielders);
        RW.getItems().addAll(strikers);

        LWB.getItems().addAll(defenders);
        LB.getItems().addAll(defenders);
        RB.getItems().addAll(defenders);
        RWB.getItems().addAll(defenders);

        GK.getItems().addAll(goalkeepers);
    }
}
