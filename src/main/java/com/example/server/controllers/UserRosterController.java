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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserRosterController {

    @PostMapping("/roster")
    public ResponseEntity<?> createRoster(
            HttpServletRequest request,
            @RequestBody Map<String, Map<String, Integer>> playerPositions) {

        HttpSession session = request.getSession(false);
        Long userId = SessionDAO.getUserId(session.getId());
        if (userId == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        try {
            UserRosterDAO userRosterDAO = new UserRosterDAO();
            userRosterDAO.createRoster(userId, playerPositions);
            return ResponseEntity.ok("Roster created successfully for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to create roster for user ID: " + userId);
        }

    }

    @GetMapping("/roster")
    public ResponseEntity<?> getRoster(HttpServletRequest request, @PathVariable String sessionId) {
        HttpSession session = request.getSession(false);
        Long userId = SessionDAO.getUserId(session.getId());
        if (userId == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        List<IUserRoster> rosterList;
        rosterList = UserRosterDAO.findTeamByUser(userId);

        if (rosterList == null || rosterList.isEmpty()) {
            return ResponseEntity.status(404).body("No roster found for user ID: " + userId);
        }

        return ResponseEntity.ok(rosterList);
    }

}
