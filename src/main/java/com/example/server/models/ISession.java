package com.example.server.models;

public class ISession {
    private Long id;
    private String session_id;
    private Long user_id;
    private Long expires_at;

    public ISession() {
    }

    public ISession(Long id, String session_id, Long user_id, Long expires_at) {
        this.id = id;
        this.session_id = session_id;
        this.user_id = user_id;
        this.expires_at = expires_at;
    }

    public ISession(Long user_id) {
        this.session_id = java.util.UUID.randomUUID().toString();
        this.user_id = user_id;
        this.expires_at = System.currentTimeMillis() + 1 * 60 * 60 * 1000; // 1 hour
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getSessionId() {
        return session_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public Long getExpiresAt() {
        return expires_at;
    }
}
