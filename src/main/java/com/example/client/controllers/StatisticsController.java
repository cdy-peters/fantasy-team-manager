package com.example.client.controllers;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import com.example.client.helpers.HttpHelper;
import com.example.interfaces.IStatistics;
import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the statistics page.
 */
public class StatisticsController implements Initializable {
    @FXML
    private TableView<IStatistics> tableView;
    @FXML
    private TableView<IStatistics> pointsView;
    @FXML
    private TableColumn<IStatistics, String> nameColumn, pointsNameColumn, teamColumn;
    @FXML
    private TableColumn<IStatistics, Integer> valueColumn, assistsColumn, redCardsColumn, yellowCardsColumn,
            minutesColumn, penaltyGoalsColumn, penaltyKicksColumn;
    @FXML
    private TableColumn<IStatistics, Double> pointsColumn, goalsPer90Column, assistsPer90Column;

    private Gson gson = new Gson();
    private ObservableList<IStatistics> statistics;

    /**
     * Default constructor
     */
    public StatisticsController() {
    }

    /**
     * Initialize the statistics page.
     * Initializing the columns and populating the tables with player statistics.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initColumn(nameColumn, "playerName");
        initColumn(teamColumn, "team");
        initColumn(valueColumn, "goals");
        initColumn(assistsColumn, "assists");
        initColumn(redCardsColumn, "redCards");
        initColumn(yellowCardsColumn, "yellowCards");
        initColumn(minutesColumn, "minutes");
        initColumn(penaltyGoalsColumn, "goalsPenaltyKicks");
        initColumn(penaltyKicksColumn, "penaltyKicks");
        initColumn(goalsPer90Column, "goalsPer90");
        initColumn(assistsPer90Column, "assistsPer90");

        initColumn(pointsNameColumn, "playerName");
        initColumn(pointsColumn, "playerScore");
        pointsColumn.setSortType(TableColumn.SortType.DESCENDING);

        statistics = fetchStatistics();
        populateTables(statistics);
    }

    /**
     * Helper method to initialize a column.
     * 
     * @param <T>
     * @param column
     * @param property
     */
    private <T> void initColumn(TableColumn<IStatistics, T> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }

    /**
     * Populate the tables with player statistics.
     * 
     * @param statisticsList
     */
    private void populateTables(List<IStatistics> statisticsList) {
        ObservableList<IStatistics> stats = FXCollections.observableArrayList(statisticsList);
        tableView.setItems(stats);
        pointsView.setItems(stats);

        pointsView.getSortOrder().add(pointsColumn);
        pointsView.sort();
    }

    /**
     * Fetch player statistics from the server.
     * 
     * @return An observable list of player statistics.
     */
    @FXML
    private ObservableList<IStatistics> fetchStatistics() {
        HttpHelper request = new HttpHelper("player_statistics");
        try {
            HttpResponse<String> response = request.send();
            return handleStatisticsResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    /**
     * Handle the statistics response.
     * 
     * @param response
     * @return An observable list of player statistics.
     */
    private ObservableList<IStatistics> handleStatisticsResponse(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            IStatistics[] statsArray = gson.fromJson(response.body(), IStatistics[].class);
            return FXCollections.observableArrayList(statsArray);
        }

        System.out.println("No statistics found");
        return FXCollections.observableArrayList();
    }
}
