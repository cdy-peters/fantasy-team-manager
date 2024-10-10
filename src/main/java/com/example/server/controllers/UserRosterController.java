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

/**
 * Controller for the user roster endpoints.
 */
@RestController
public class UserRosterController {
    SessionDAO sessionDAO = new SessionDAO();

    /**
     * Default constructor
     */
    public UserRosterController() {
    }

    /**
     * Create a roster for the user
     * 
     * @param token  The user's session token
     * @param roster The user's roster
     * @return The response entity
     */
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

    /**
     * Get the roster of the user making the request.
     * User must be authenticated.
     * 
     * @param token The user's session token
     * @return The response entity
     */
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

    /**
     * Get all rosters
     * 
     * @return The response entity
     */
    @GetMapping("/rosters")
    public ResponseEntity<?> getRosters() {

        List<ILeaderboardElement> rosterList;
        rosterList = UserRosterDAO.getRosters();

        return ResponseEntity.ok(rosterList);
    }
}
