package com.example.client.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.example.client.helpers.Statistics;
import com.example.client.helpers.Utils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private TableView<Statistics> tableView;

    @FXML
    private TableView<Statistics> metersView;

    @FXML
    private TableView<Statistics> scoreView;

    @FXML
    private TableView<Statistics> cardView;

    @FXML
    private TableColumn<Statistics, String> nameColumn;

    @FXML
    private TableColumn<Statistics, String> scoreNameColumn;

    @FXML
    private TableColumn<Statistics, String> cardsNameColumn;

    @FXML
    private TableColumn<Statistics, Integer> valueColumn;

    @FXML
    private TableColumn<Statistics, Integer> assistsColumn;

    @FXML
    private TableColumn<Statistics, Integer> totalColumn;

    @FXML
    private TableColumn<Statistics, Integer> redCardsColumn;

    @FXML
    private TableColumn<Statistics, Integer> yellowCardsColumn;

    @FXML
    private TableColumn<Statistics, Integer> totalCardsColumn;

    @FXML
    private TableColumn<Statistics, Integer> carriesColumn;

    @FXML
    private TableColumn<Statistics, Integer> passesColumn;

    @FXML
    private TableColumn<Statistics, Integer> runsColumn;

    @FXML
    private TableColumn<Statistics, String> metersNameColumn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
        assistsColumn.setCellValueFactory(new PropertyValueFactory<>("assists"));

        scoreNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        totalColumn.setCellValueFactory(cellData -> {
            Statistics stats = cellData.getValue();
            int total = stats.getGoals() + stats.getAssists();
            return new javafx.beans.property.SimpleIntegerProperty(total).asObject();
        });
        totalColumn.setSortType(TableColumn.SortType.DESCENDING);  // Sort by totalColumn by default in descending order

        cardsNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        redCardsColumn.setCellValueFactory(new PropertyValueFactory<>("redCards"));
        yellowCardsColumn.setCellValueFactory(new PropertyValueFactory<>("yellowCards"));
        totalCardsColumn.setCellValueFactory(cellData -> {
            Statistics stats = cellData.getValue();
            int total = stats.getRedCards() + stats.getYellowCards();
            return new javafx.beans.property.SimpleIntegerProperty(total).asObject();
        });
        totalCardsColumn.setSortType(TableColumn.SortType.DESCENDING);  // Sort by totalCardsColumn by default in descending order
    
        metersNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        carriesColumn.setCellValueFactory(new PropertyValueFactory<>("progressiveCarries"));
        passesColumn.setCellValueFactory(new PropertyValueFactory<>("progressivePasses"));
        runsColumn.setCellValueFactory(new PropertyValueFactory<>("progressiveRuns"));

        // Call to listUsers to populate the table with data
        listUsers();
    }

    private void populateTables(List<Statistics> statisticsList) {
        tableView.getItems().clear();
        scoreView.getItems().clear();
        cardView.getItems().clear();
        metersView.getItems().clear();

        tableView.getItems().addAll(statisticsList);

        metersView.getItems().addAll(statisticsList);

        scoreView.getItems().addAll(statisticsList);
        scoreView.getSortOrder().add(totalColumn);  // Add column to sort order
        scoreView.sort();  // Apply the sort after data is populated

        cardView.getItems().addAll(statisticsList);
        cardView.getSortOrder().add(totalCardsColumn);  // Add column to sort order
        cardView.sort();  // Apply the sort after data is populated
    }

    @FXML
    private void listUsers() {
        System.out.println("\nListing users using Java 11 HttpClient:");

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Utils.USER_API)).GET().build();

        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

            List<Statistics> statistics = Utils.toList(response.body());
            System.out.println("Statistics fetched: " + statistics);

            // Populate the table with the statistics list
            Platform.runLater(() -> populateTables(statistics));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Log error for easier debugging
        }
    }
}
