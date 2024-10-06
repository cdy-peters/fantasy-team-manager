package com.example.server.models;

import com.example.server.Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRosterDAO {

    public static void createRoster(Long userId, IUserRoster roster) {
        String query = "INSERT INTO user_roster (user_id, roster_score, position1_player_id, position2_player_id, position3_player_id, position4_player_id, position5_player_id, position6_player_id, position7_player_id, position8_player_id, position9_player_id, position10_player_id, position11_player_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = Server.conn.prepareStatement(query);
            stmt.setLong(1, userId);
            stmt.setDouble(2, roster.getScore());
            stmt.setLong(3, roster.getPosition1());
            stmt.setLong(4, roster.getPosition2());
            stmt.setLong(5, roster.getPosition3());
            stmt.setLong(6, roster.getPosition4());
            stmt.setLong(7, roster.getPosition5());
            stmt.setLong(8, roster.getPosition6());
            stmt.setLong(9, roster.getPosition7());
            stmt.setLong(10, roster.getPosition8());
            stmt.setLong(11, roster.getPosition9());
            stmt.setLong(12, roster.getPosition10());
            stmt.setLong(13, roster.getPosition11());

            stmt.executeUpdate();
            System.out.println("Roster created successfully for user ID: " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find a roster by user ID
    public static IUserRoster findRosterByUser(Long userId) {
        String query = String.format("SELECT * FROM user_roster WHERE user_id = '%s'", userId);

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
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

                return new IUserRoster(id, position1, position2, position3, position4, position5, position6, position7,
                        position8, position9, position10, position11, sub1, sub2, sub3, sub4);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ILeaderboardElement> getRosters() {
        String query = String.format(
                "SELECT ur.*, u.username, ROW_NUMBER() OVER (ORDER BY ur.roster_score DESC) AS rank FROM user_roster ur JOIN user u ON ur.user_id = u.id");
        List<ILeaderboardElement> rosterList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                int rank = rs.getInt("rank");
                Long userId = rs.getLong("user_id");
                String username = rs.getString("username");
                int rosterScore = rs.getInt("roster_score");
                ILeaderboardElement roster = new ILeaderboardElement(id, rank, userId, username, rosterScore);
                rosterList.add(roster);
            }

            if (rosterList.isEmpty()) {
                System.out.println("No teams found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rosterList;
    }
}
