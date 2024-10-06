package com.example.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.ILeaderboardElement;
import com.example.server.models.IUserRoster;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserRosterDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserRosterController {

    @PostMapping("/roster")
    public ResponseEntity<?> createRoster(
            HttpServletRequest request,
            @RequestBody IUserRoster roster) {

        HttpSession session = request.getSession(false);
        Long userId = SessionDAO.getUserId(session.getId());
        if (userId == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        try {
            UserRosterDAO.createRoster(userId, roster);
            return ResponseEntity.ok("Roster created successfully for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to create roster for user ID: " + userId);
        }

    }

    @GetMapping("/roster")
    public ResponseEntity<?> getRoster(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        Long userId = SessionDAO.getUserId(session.getId());
        if (userId == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        IUserRoster roster = UserRosterDAO.findRosterByUser(userId);
        if (roster == null) {
            return ResponseEntity.status(404).body("Roster not found for user ID: " + userId);
        }

        return ResponseEntity.ok(roster);
    }

    @GetMapping("/rosters")
    public ResponseEntity<?> getRosters(HttpServletRequest request) {

        List<ILeaderboardElement> rosterList;
        rosterList = UserRosterDAO.getRosters();

        return ResponseEntity.ok(rosterList);
    }
}
