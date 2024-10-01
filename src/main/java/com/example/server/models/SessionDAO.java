package com.example.server.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;

import com.example.server.Server;

import jakarta.servlet.http.HttpSession;

public class SessionDAO {

    public static Long getUserId(String sessionId) {
        String query = String.format("SELECT user_id FROM session WHERE session_id = '%s'", sessionId);
        try {
            Statement stmt = Server.conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getLong("user_id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void create(HttpSession session) {
        String query = "INSERT INTO session (session_id, created_at, max_interval, user_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = Server.conn.prepareStatement(query);
            stmt.setString(1, session.getId());
            stmt.setTimestamp(2, new Timestamp(session.getCreationTime()));
            stmt.setInt(3, session.getMaxInactiveInterval());
            stmt.setLong(4, (Long) session.getAttribute("userId"));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String sessionId) {
        String query = "DELETE FROM session WHERE session_id = ?";
        try {
            PreparedStatement stmt = Server.conn.prepareStatement(query);
            stmt.setString(1, sessionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
