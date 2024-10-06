package com.example.client.controllers;

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

    private void createPlayerCard(HBox hBox, String name) {
        VBox vBox = new VBox();
        vBox.setSpacing(2.0);
        vBox.setStyle("-fx-border-color: black; -fx-border-width: 0.7; -fx-border-radius: 2;");
        vBox.setPadding(new Insets(2, 5, 2, 5));

        Text text = new Text("Name");
        text.setStrokeWidth(0.0);

        vBox.getChildren().add(text);

        hBox.getChildren().add(vBox);
    }

    public void initialize() {
        // Forwards
        createPlayerCard(forwards, "Player 1");
        createPlayerCard(forwards, "Player 2");

        // Midfielders
        createPlayerCard(midfielders, "Player 3");
        createPlayerCard(midfielders, "Player 4");
        createPlayerCard(midfielders, "Player 5");
        createPlayerCard(midfielders, "Player 6");

        // Defenders
        createPlayerCard(defenders, "Player 7");
        createPlayerCard(defenders, "Player 8");
        createPlayerCard(defenders, "Player 9");
        createPlayerCard(defenders, "Player 10");

        // Goalkeeper
        createPlayerCard(goalkeeper, "Player 11");
    }
}
