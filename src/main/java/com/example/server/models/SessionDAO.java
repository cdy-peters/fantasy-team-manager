package com.example.server.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;

import com.example.server.Server;

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

    public ISession find(String sessionId) {
        String query = String.format("SELECT * FROM session WHERE session_id = '%s'", sessionId);
        try {
            Statement stmt = Server.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                Long id = rs.getLong("id");
                Long userId = rs.getLong("user_id");
                Long expiresAt = rs.getTimestamp("expires_at").getTime();
                return new ISession(id, sessionId, userId, expiresAt);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ISession create(Long userId) throws Exception {
        ISession session = new ISession(userId);

        String query = "INSERT INTO session (session_id, user_id, expires_at) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = Server.conn.prepareStatement(query);
            stmt.setString(1, session.getSessionId());
            stmt.setLong(2, session.getUserId());
            stmt.setTimestamp(3, new Timestamp(session.getExpiresAt()));
            stmt.executeUpdate();

            return session;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new Exception("Error creating session");
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
