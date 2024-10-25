package com.example.client.controllers;

import com.example.interfaces.IPlayer;
import com.example.interfaces.IStatistics;

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

/**
 * Controller for player cards.
 */
public class PlayerCardController {
    /**
     * Default constructor
     */
    public PlayerCardController() {
    }

    /**
     * Clip children of a region
     * 
     * @param region The region to clip
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
     * Add an image to a VBox
     * 
     * @param vBox     The VBox to add the image to
     * @param filePath The file path of the image
     */
    private void addImage(VBox vBox, String filePath) {
        Pane pane = new Pane();
        pane.setMaxWidth(Region.USE_PREF_SIZE);

        Image image = new Image(getClass().getResourceAsStream(filePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        pane.getChildren().add(imageView);

        clipChildren(pane);
        vBox.getChildren().add(pane);
    }

    /**
     * Create a placeholder card with a handler for player selection
     * 
     * @param hBox             The HBox to add the player card to
     * @param playerPosition   The player's position
     * @param positionId       The player's position ID
     * @param handlePlayerCard The handler for player selection
     */
    public void create(HBox hBox, String playerPosition, Integer positionId,
            EventHandler<ActionEvent> handlePlayerCard) {
        Button playerCard = new Button();
        playerCard.getStyleClass().add("player-card");
        playerCard.setPrefSize(100, 100);
        playerCard.setOnAction(handlePlayerCard);
        playerCard.setId("playerCard" + positionId);

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
     * Create a player card with player info
     * 
     * @param hBox       The HBox to add the player card to
     * @param player     The player
     * @param positionId The player's position ID
     */
    public void create(HBox hBox, IStatistics player, Integer positionId) {
        Button playerCard = new Button();
        playerCard.getStyleClass().add("player-card");
        playerCard.setPrefSize(100, 100);
        playerCard.setId("playerCard" + positionId);

        // Graphic
        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Image
        String filePath = "/images/shirts/" + player.getTeam() + ".png";
        addImage(vBox, filePath);

        // Info
        VBox info = new VBox();
        info.setAlignment(javafx.geometry.Pos.CENTER);

        Label name = new Label(player.getPlayerName());
        name.setMaxWidth(Region.USE_PREF_SIZE);
        info.getChildren().add(name);

        Label score = new Label("Score: " + (int) Math.rint(player.getPlayerScore()));
        score.setMaxWidth(Region.USE_PREF_SIZE);
        info.getChildren().add(score);

        vBox.getChildren().add(info);

        playerCard.setGraphic(vBox);

        hBox.getChildren().add(playerCard);
    }

    /**
     * Update a player card with player info
     * 
     * @param playerCard The player card to update
     * @param player     The player
     */
    public void update(Button playerCard, IPlayer player) {
        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Image
        String filePath = "/images/shirts/" + player.getTeam() + ".png";
        addImage(vBox, filePath);

        // Info
        VBox info = new VBox();
        info.setAlignment(javafx.geometry.Pos.CENTER);

        Label name = new Label(player.getName());
        name.setMaxWidth(Region.USE_PREF_SIZE);
        info.getChildren().add(name);

        Label price = new Label("Price: $" + player.getPrice());
        price.setMaxWidth(Region.USE_PREF_SIZE);
        info.getChildren().add(price);

        vBox.getChildren().add(info);

        playerCard.setGraphic(vBox);
    }
}
