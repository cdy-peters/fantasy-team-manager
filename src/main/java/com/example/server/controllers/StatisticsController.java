package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.interfaces.IStatistics;
import com.example.server.models.PlayerStatisticsDAO;

/**
 * Controller for the player statistics endpoints.
 */
@RestController
public class StatisticsController {
    private PlayerStatisticsDAO playerStatisticsDAO = new PlayerStatisticsDAO();

    /**
     * Default constructor
     */
    public StatisticsController() {
    }

    /**
     * Get player statistics by playerId if a playerId is provided, otherwise get
     * all statistics.
     * 
     * @param playerId The player's ID
     * @return A list of player statistics
     */
    @GetMapping("/player_statistics")
    public ResponseEntity<?> getPlayerStatistics(@RequestParam(required = false) String playerId) {
        List<IStatistics> statisticsList;

        if (playerId == null || playerId.isEmpty()) {
            // If no playerId is provided, retrieve all statistics
            statisticsList = playerStatisticsDAO.findAllStatistics();
        } else {
            // If playerId is provided, retrieve statistics for that specific player
            statisticsList = playerStatisticsDAO.findByPlayerId(playerId);
        }

        if (statisticsList == null || statisticsList.isEmpty()) {
            return ResponseEntity.status(404).body("No statistics found");
        }

        return ResponseEntity.ok(statisticsList);
    }
}
