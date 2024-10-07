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
    private TableView<Statistics> pointsView;

    @FXML
    private TableColumn<Statistics, String> nameColumn;
    
    @FXML
    private TableColumn<Statistics, Integer> valueColumn;

    @FXML
    private TableColumn<Statistics, Integer> assistsColumn;

    @FXML
    private TableColumn<Statistics, Integer> redCardsColumn;

    @FXML
    private TableColumn<Statistics, Integer> yellowCardsColumn;

    @FXML
    private TableColumn<Statistics, Integer> pointsColumn;

    @FXML
    private TableColumn<Statistics, String> pointsNameColumn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
        assistsColumn.setCellValueFactory(new PropertyValueFactory<>("assists"));

        redCardsColumn.setCellValueFactory(new PropertyValueFactory<>("redCards"));
        yellowCardsColumn.setCellValueFactory(new PropertyValueFactory<>("yellowCards"));

        pointsNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        pointsColumn.setSortType(TableColumn.SortType.DESCENDING);

        // Call to listUsers to populate the table with data
        listUsers();
    }

    private void populateTables(List<Statistics> statisticsList) {
        tableView.getItems().clear();
        pointsView.getItems().clear();

        tableView.getItems().addAll(statisticsList);

        pointsView.getItems().addAll(statisticsList);
        pointsView.getSortOrder().add(pointsColumn);  // Add column to sort order
        pointsView.sort();  // Apply the sort after data is populated


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
