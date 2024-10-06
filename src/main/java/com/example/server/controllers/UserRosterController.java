package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.ILeaderboardElement;
import com.example.server.models.ISession;
import com.example.server.models.IUserRoster;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserRosterDAO;

@RestController
public class UserRosterController {
    SessionDAO sessionDAO = new SessionDAO();

    @PostMapping("/roster")
    public ResponseEntity<?> createRoster(
            @RequestHeader(name = "Authorization", required = true) String token,
            @RequestBody IUserRoster roster) {

        ISession session = sessionDAO.find(token);
        if (session == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Long userId = session.getUserId();
        try {
            UserRosterDAO.createRoster(userId, roster);
            return ResponseEntity.ok("Roster created successfully for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to create roster for user ID: " + userId);
        }

    }

    @GetMapping("/roster")
    public ResponseEntity<?> getRoster(@RequestHeader(name = "Authorization", required = false) String token) {
        ISession session = sessionDAO.find(token);
        if (session == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Long userId = session.getUserId();
        IUserRoster roster = UserRosterDAO.findRosterByUser(userId);
        if (roster == null) {
            return ResponseEntity.status(404).body("Roster not found for user ID: " + userId);
        }

        return ResponseEntity.ok(roster);
    }

    @GetMapping("/rosters")
    public ResponseEntity<?> getRosters() {

        List<ILeaderboardElement> rosterList;
        rosterList = UserRosterDAO.getRosters();

        return ResponseEntity.ok(rosterList);
    }
}
