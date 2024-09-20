package com.example.server.controllers;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.IUser;
import com.example.server.models.IUserRoster;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;
import com.example.server.models.UserRosterDAO;
import com.example.server.Server; // Import the Server class

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserRosterController {

    @PutMapping("/roster/{userId}")
    public ResponseEntity<?> updateRosterWithPlayers(
            @PathVariable Long userId,
            @RequestBody Map<String, Long> playerPositions) {

        UserRosterDAO userRosterDAO = new UserRosterDAO();
        boolean success = userRosterDAO.updateRosterWithPlayers(userId, playerPositions);

        if (success) {
            return ResponseEntity.ok("Roster updated successfully");
        } else {
            return ResponseEntity.status(404).body("Failed to update roster for user ID: " + userId);
        }
    }

    @GetMapping("/roster/{userId}")
    public ResponseEntity<?> getRoster(@PathVariable Long userId) {
        List<IUserRoster> rosterList;
        rosterList = UserRosterDAO.findTeamByUser(userId);

        if (rosterList == null || rosterList.isEmpty()) {
            return ResponseEntity.status(404).body("No roster found for user ID: " + userId);
        }

        return ResponseEntity.ok(rosterList);
    }

}
