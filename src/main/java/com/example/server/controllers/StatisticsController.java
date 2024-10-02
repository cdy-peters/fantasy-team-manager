package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IStatistics;
import com.example.server.models.PlayerStatisticsDAO;

@RestController
public class StatisticsController {

    @GetMapping("/player_statistics")
    public ResponseEntity<?> getPlayerStatistics(@RequestParam(required = false) String playerId) {
        List<IStatistics> statisticsList;

        if (playerId == null || playerId.isEmpty()) {
            // If no playerId is provided, retrieve all statistics
            statisticsList = PlayerStatisticsDAO.findAllStatistics();
        } else {
            // If playerId is provided, retrieve statistics for that specific player
            statisticsList = PlayerStatisticsDAO.findByPlayerId(playerId);
        }

        if (statisticsList == null || statisticsList.isEmpty()) {
            return ResponseEntity.status(404).body("No statistics found");
        }

        return ResponseEntity.ok(statisticsList);
    }
}
