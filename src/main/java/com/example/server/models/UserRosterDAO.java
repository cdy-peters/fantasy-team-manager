package com.example.server.models;

import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRosterDAO {

    public boolean updateRosterWithPlayers(Long userId, Map<String, Long> playerPositions) {
        if (playerPositions == null || playerPositions.isEmpty()) {
            System.out.println("No players provided to update");
            return false;
        }

        // Start building the SQL query
        StringBuilder query = new StringBuilder("UPDATE user_roster SET ");

        // Loop through each position and append the update statement
        for (Map.Entry<String, Long> entry : playerPositions.entrySet()) {
            String position = entry.getKey();
            Long playerId = entry.getValue();

            if (isValidPosition(position)) {
                query.append(position).append(" = ").append(playerId).append(", ");
            }
        }

        // Remove the trailing comma and space
        query.setLength(query.length() - 2);
        query.append(" WHERE user_id = ").append(userId);

        try {
            Statement stmt = Server.conn.createStatement();
            int rowsUpdated = stmt.executeUpdate(query.toString());

            if (rowsUpdated > 0) {
                System.out.println("Roster updated successfully for user ID: " + userId);
                return true;
            } else {
                System.out.println("Roster not found for user ID: " + userId);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to validate the position field names
    private boolean isValidPosition(String position) {
        return position.matches("position[1-9]_player_id|position1[0-1]_player_id|sub[1-4]_player_id");
    }

    // Method to find player statistics by player ID
    public static List<IUserRoster> findTeamByUser(Long userId) {
        String query = String.format("SELECT * FROM user_roster WHERE user_id = '%s'", userId);
        List<IUserRoster> rosterList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long position1 = rs.getLong("position1_player_id");
                Long position2 = rs.getLong("position2_player_id");
                Long position3 = rs.getLong("position3_player_id");
                Long position4 = rs.getLong("position4_player_id");
                Long position5 = rs.getLong("position5_player_id");
                Long position6 = rs.getLong("position6_player_id");
                Long position7 = rs.getLong("position7_player_id");
                Long position8 = rs.getLong("position8_player_id");
                Long position9 = rs.getLong("position9_player_id");
                Long position10 = rs.getLong("position10_player_id");
                Long position11 = rs.getLong("position11_player_id");
                Long sub1 = rs.getLong("sub1_player_id");
                Long sub2 = rs.getLong("sub2_player_id");
                Long sub3 = rs.getLong("sub3_player_id");
                Long sub4 = rs.getLong("sub4_player_id");

                IUserRoster roster = new IUserRoster(id, position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, sub1, sub2, sub3, sub4);
                rosterList.add(roster);
            }

            if (rosterList.isEmpty()) {
                System.out.println("No team found for: " + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rosterList;
    }
}
