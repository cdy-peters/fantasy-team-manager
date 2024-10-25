package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.interfaces.IPlayer;
import com.example.server.models.PlayerDAO;

/**
 * Controller for the player endpoints.
 */
@RestController
public class PlayerController {
    private PlayerDAO playerDAO = new PlayerDAO();

    /**
     * Default constructor
     */
    public PlayerController() {
    }

    /**
     * Get players by position if a position is provided, otherwise get all players.
     * 
     * @param position The player's position
     * @return A list of players
     */
    @GetMapping("/players")
    public ResponseEntity<?> getPlayers(@RequestParam(required = false) String position) {
        List<IPlayer> playerList;

        if (position != null && !position.isEmpty()) {
            playerList = playerDAO.findPlayersByPosition(position);
        } else {
            playerList = playerDAO.findAllPlayers();
        }

        if (playerList == null || playerList.isEmpty()) {
            return ResponseEntity.status(404).body("No players found");
        }

        return ResponseEntity.ok(playerList);
    }
}
