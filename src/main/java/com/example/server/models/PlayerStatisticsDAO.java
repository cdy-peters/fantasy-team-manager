package com.example.server.models;

import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the player statistics.
 */
public class PlayerStatisticsDAO {

    /**
     * Default constructor.
     */
    public PlayerStatisticsDAO() {
    }

    /**
     * Find player statistics by player ID.
     * 
     * @param playerId The ID of the player
     * @return A list of IStatistics objects
     */
    public static List<IStatistics> findByPlayerId(String playerId) {
        String query = String.format("SELECT * FROM player_statistics WHERE id = '%s'", playerId);
        List<IStatistics> statisticsList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String player_name = rs.getString("player_name");
                int games_played = rs.getInt("minutes_played");
                int starts = rs.getInt("starts");
                String nation = rs.getString("nation");
                String position = rs.getString("position");
                int age = rs.getInt("age");
                int minutes = rs.getInt("minutes");
                int ninetyMinutes = rs.getInt("90s");
                int goals = rs.getInt("goals");
                int assists = rs.getInt("assists");
                int goalsAssists = rs.getInt("g+a");
                int goalsPenaltyKicks = rs.getInt("g+pk");
                int penaltyKicks = rs.getInt("penalty_kicks");
                int penaltyKickAttempts = rs.getInt("penalty_kick_attempts");
                int yellowCards = rs.getInt("yellow_cards");
                int redCards = rs.getInt("red_cards");
                double expectedGoals = rs.getDouble("expected_goals");
                double nonPenaltyExpectedGoals = rs.getDouble("non_penalty_expected_goals");
                double expectedAssists = rs.getDouble("expected_assists");
                double npXgPlusXA = rs.getDouble("npXg+XA");
                int progressiveCarries = rs.getInt("proggresive_carries");
                int progressivePasses = rs.getInt("proggresive_passes");
                int progressiveRuns = rs.getInt("proggresive_runs");
                double goalsPer90 = rs.getDouble("goals_90");
                double assistsPer90 = rs.getDouble("assists_90");
                double goalsAssistsPer90 = rs.getDouble("g+a_90");
                double goalsPenaltyKicksPer90 = rs.getDouble("g+pk_90");
                double goalsAssistsPenaltyKicksPer90 = rs.getDouble("g+a-pk_90");
                double xGPer90 = rs.getDouble("xG_90");
                double xAPer90 = rs.getDouble("xA_90");
                double xGPlusXAPer90 = rs.getDouble("xG+xA_90");
                double npxGPer90 = rs.getDouble("npxG_90");
                double npxGPlusXAPer90 = rs.getDouble("npxG+xA_90");
                String team = rs.getString("team");
                double playerScore = rs.getDouble("player_score");

                IStatistics statistics = new IStatistics(id, player_name, games_played, starts, nation, position, age,
                        minutes, ninetyMinutes, goals, assists, goalsAssists, goalsPenaltyKicks, penaltyKicks,
                        penaltyKickAttempts, yellowCards, redCards, expectedGoals, nonPenaltyExpectedGoals,
                        expectedAssists, npXgPlusXA, progressiveCarries, progressivePasses, progressiveRuns, goalsPer90,
                        assistsPer90, goalsAssistsPer90, goalsPenaltyKicksPer90, goalsAssistsPenaltyKicksPer90, xGPer90,
                        xAPer90, xGPlusXAPer90, npxGPer90, npxGPlusXAPer90, team, playerScore);
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

    /**
     * Find all player statistics.
     * 
     * @return A list of IStatistics objects
     */
    public static List<IStatistics> findAllStatistics() {
        List<IStatistics> statisticsList = new ArrayList<>();
        String query = "SELECT * FROM player_statistics";

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String player_name = rs.getString("player_name");
                int games_played = rs.getInt("minutes_played");
                int starts = rs.getInt("starts");
                String nation = rs.getString("nation");
                String position = rs.getString("position");
                int age = rs.getInt("age");
                int minutes = rs.getInt("minutes");
                int ninetyMinutes = rs.getInt("90s");
                int goals = rs.getInt("goals");
                int assists = rs.getInt("assists");
                int goalsAssists = rs.getInt("g+a");
                int goalsPenaltyKicks = rs.getInt("g+pk");
                int penaltyKicks = rs.getInt("penalty_kicks");
                int penaltyKickAttempts = rs.getInt("penalty_kick_attempts");
                int yellowCards = rs.getInt("yellow_cards");
                int redCards = rs.getInt("red_cards");
                double expectedGoals = rs.getDouble("expected_goals");
                double nonPenaltyExpectedGoals = rs.getDouble("non_penalty_expected_goals");
                double expectedAssists = rs.getDouble("expected_assists");
                double npXgPlusXA = rs.getDouble("npXg+XA");
                int progressiveCarries = rs.getInt("proggresive_carries");
                int progressivePasses = rs.getInt("proggresive_passes");
                int progressiveRuns = rs.getInt("proggresive_runs");
                double goalsPer90 = rs.getDouble("goals_90");
                double assistsPer90 = rs.getDouble("assists_90");
                double goalsAssistsPer90 = rs.getDouble("g+a_90");
                double goalsPenaltyKicksPer90 = rs.getDouble("g+pk_90");
                double goalsAssistsPenaltyKicksPer90 = rs.getDouble("g+a-pk_90");
                double xGPer90 = rs.getDouble("xG_90");
                double xAPer90 = rs.getDouble("xA_90");
                double xGPlusXAPer90 = rs.getDouble("xG+xA_90");
                double npxGPer90 = rs.getDouble("npxG_90");
                double npxGPlusXAPer90 = rs.getDouble("npxG+xA_90");
                String team = rs.getString("team");
                double playerScore = rs.getDouble("player_score");

                IStatistics statistics = new IStatistics(id, player_name, games_played, starts, nation, position, age,
                        minutes, ninetyMinutes, goals, assists, goalsAssists, goalsPenaltyKicks, penaltyKicks,
                        penaltyKickAttempts, yellowCards, redCards, expectedGoals, nonPenaltyExpectedGoals,
                        expectedAssists, npXgPlusXA, progressiveCarries, progressivePasses, progressiveRuns, goalsPer90,
                        assistsPer90, goalsAssistsPer90, goalsPenaltyKicksPer90, goalsAssistsPenaltyKicksPer90, xGPer90,
                        xAPer90, xGPlusXAPer90, npxGPer90, npxGPlusXAPer90, team, playerScore);
                statisticsList.add(statistics);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statisticsList;
    }
}