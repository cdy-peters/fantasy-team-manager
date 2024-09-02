package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IStatistics;
import com.example.server.models.PlayerStatisticsDAO;

@RestController
public class StatisticsController {

    @GetMapping("/player_statistics/{playerId}")
    public ResponseEntity<?> getPlayerStatistics(@PathVariable String playerId) {
        // Validation
        if (playerId == null || playerId.isEmpty()) {
            return ResponseEntity.status(400).body("Missing player ID");
        }

        // Retrieve player statistics from the database
        List<IStatistics> statistics = PlayerStatisticsDAO.findByPlayerId(playerId);
        
        if (statistics == null || statistics.isEmpty()) {
            return ResponseEntity.status(404).body("No statistics found for player ID: " + playerId);
        }

        return ResponseEntity.ok(statistics);
    }
}
