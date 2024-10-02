package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IPlayer;
import com.example.server.models.PlayerDAO;

@RestController
public class PlayerController {
    @GetMapping("/players")
    public ResponseEntity<?> getPlayers(@RequestParam(required = false) String position) {
        List<IPlayer> playerList;

        if (position != null && !position.isEmpty()) {
            playerList = PlayerDAO.findPlayersByPosition(position);
        } else {
            playerList = PlayerDAO.findAllPlayers();
        }

        if (playerList == null || playerList.isEmpty()) {
            return ResponseEntity.status(404).body("No players found");
        }

        return ResponseEntity.ok(playerList);
    }
}
