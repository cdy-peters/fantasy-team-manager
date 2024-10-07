package com.example.client.controllers;

import com.example.server.models.IPlayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayerCardController {
    /**
     * Clip children of a region
     * 
     * @param region
     * 
     * @see <a href=
     *      "https://stackoverflow.com/a/61121454/18405522">StackOverflow</a>
     */
    private void clipChildren(Region region) {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(region.widthProperty());
        clip.heightProperty().bind(region.heightProperty());
        region.setClip(clip);
    }

    /**
     * Create a placeholder card with a handler for player selection
     * 
     * @param hBox
     * @param playerPosition
     * @param playerId
     * @param handlePlayerCard
     */
    public void create(HBox hBox, String playerPosition, Integer playerId,
            EventHandler<ActionEvent> handlePlayerCard) {
        Button playerCard = new Button();
        playerCard.getStyleClass().add("player-card");
        playerCard.setPrefSize(100, 100);
        playerCard.setOnAction(handlePlayerCard);
        playerCard.setId("playerCard" + playerId);

        // Graphic
        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setSpacing(10.0);

        Text plus = new Text("+");
        Text position = new Text(playerPosition);

        vBox.getChildren().add(plus);
        vBox.getChildren().add(position);

        playerCard.setGraphic(vBox);

        hBox.getChildren().add(playerCard);
    }

    /**
     * Update a player card with player info
     * 
     * @param playerCard
     * @param player
     */
    public void update(Button playerCard, IPlayer player) {
        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Image
        Pane pane = new Pane();
        pane.setMaxWidth(Region.USE_PREF_SIZE);

        String filePath = "/images/shirts/" + player.getTeam() + ".png";
        Image image = new Image(getClass().getResourceAsStream(filePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        pane.getChildren().add(imageView);

        clipChildren(pane);
        vBox.getChildren().add(pane);

        // Info
        VBox info = new VBox();
        info.setAlignment(javafx.geometry.Pos.CENTER);

        Label name = new Label(player.getName());
        name.setMaxWidth(Region.USE_PREF_SIZE);
        info.getChildren().add(name);

        vBox.getChildren().add(info);

        playerCard.setGraphic(vBox);
    }
}
