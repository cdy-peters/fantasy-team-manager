package com.example.server.models;

import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatisticsDAO {

    // Method to find player statistics by player ID
    public static List<IStatistics> findByPlayerId(String playerId) {
        String query = String.format("SELECT * FROM player_statistics WHERE id = '%s'", playerId);
        List<IStatistics> statisticsList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                int goals = rs.getInt("goals");
                int assists = rs.getInt("assists");

                IStatistics statistics = new IStatistics(id, playerId, goals, assists);
                statisticsList.add(statistics);
            }

            if (statisticsList.isEmpty()) {
                System.out.println("No statistics found for player ID: " + playerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statisticsList;
    }
}