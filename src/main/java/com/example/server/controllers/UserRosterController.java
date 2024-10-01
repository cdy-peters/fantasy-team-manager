package com.example.server.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IUserRoster;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserRosterDAO;

@RestController
public class UserRosterController {

    @PostMapping("/roster/{userId}")
    public ResponseEntity<?> createRoster(
            @PathVariable Long userId,
            @RequestBody Map<String, Long> playerPositions) {

        try {
            UserRosterDAO userRosterDAO = new UserRosterDAO();
            userRosterDAO.createRoster(userId, playerPositions);
            return ResponseEntity.ok("Roster created successfully for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to create roster for user ID: " + userId);
        }

    }

    @PutMapping("/roster/{sessionId}")
    public ResponseEntity<?> updateRosterWithPlayers(
            @PathVariable String sessionId,
            @RequestBody Map<String, Long> playerPositions) {

        Long userId = SessionDAO.getUserId(sessionId);

        UserRosterDAO userRosterDAO = new UserRosterDAO();
        boolean success = userRosterDAO.updateRosterWithPlayers(userId, playerPositions);

        if (success) {
            return ResponseEntity.ok("Roster updated successfully");
        } else {
            return ResponseEntity.status(404).body("Failed to update roster for user ID: " + userId);
        }
    }

    @GetMapping("/roster/{sessionId}")
    public ResponseEntity<?> getRoster(@PathVariable String sessionId) {
        Long userId = SessionDAO.getUserId(sessionId);

        List<IUserRoster> rosterList;
        rosterList = UserRosterDAO.findTeamByUser(userId);

        if (rosterList == null || rosterList.isEmpty()) {
            return ResponseEntity.status(404).body("No roster found for user ID: " + userId);
        }

        return ResponseEntity.ok(rosterList);
    }

}
