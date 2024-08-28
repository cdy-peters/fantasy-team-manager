package com.example.server.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.example.server.Server;

import jakarta.servlet.http.HttpSession;

public class SessionDAO {

    public void create(HttpSession session) {
        String query = "INSERT INTO session (session_id, created_at, max_interval, username) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = Server.conn.prepareStatement(query);
            stmt.setString(1, session.getId());
            stmt.setTimestamp(2, new Timestamp(session.getCreationTime()));
            stmt.setInt(3, session.getMaxInactiveInterval());
            stmt.setString(4, (String) session.getAttribute("username"));
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
