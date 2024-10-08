package com.example.server.models;

import com.example.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public static List<IPlayer> findAllPlayers() {
        String query = "SELECT * FROM player_statistics";
        List<IPlayer> playersList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String player_name = rs.getString("player_name");
                String nation = rs.getString("nation");
                String position = rs.getString("position");
                String team = rs.getString("team");
                int score = rs.getInt("player_score");

                IPlayer player = new IPlayer(id, player_name, nation, position, team, score);
                playersList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playersList;
    }

    public static List<IPlayer> findPlayersByPosition(String position) {
        String query = String.format("SELECT * FROM player_statistics WHERE position LIKE '%%%s%%'", position);
        List<IPlayer> playersList = new ArrayList<>();

        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String player_name = rs.getString("player_name");
                String nation = rs.getString("nation");
                String player_position = rs.getString("position");
                String team = rs.getString("team");
                int score = rs.getInt("player_score");

                IPlayer player = new IPlayer(id, player_name, nation, player_position, team, score);
                playersList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playersList;
    }
}
