package com.example.server.models;

/**
 * Interface for the session.
 */
public class ISession {
    private Long id;
    private String session_id;
    private Long user_id;
    private Long expires_at;

    /**
     * Create a new ISession instance.
     */
    public ISession() {
    }

    /**
     * Create a new ISession instance.
     * 
     * @param id         The ID
     * @param session_id The session ID
     * @param user_id    The user ID
     * @param expires_at The expiration time
     */
    public ISession(Long id, String session_id, Long user_id, Long expires_at) {
        this.id = id;
        this.session_id = session_id;
        this.user_id = user_id;
        this.expires_at = expires_at;
    }

    /**
     * Create a new ISession instance.
     * 
     * @param user_id The user ID
     */
    public ISession(Long user_id) {
        this.session_id = java.util.UUID.randomUUID().toString();
        this.user_id = user_id;
        this.expires_at = System.currentTimeMillis() + 1 * 60 * 60 * 1000; // 1 hour
    }

    // Getters
    /**
     * Get the ID of the session.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the session ID.
     * 
     * @return The session ID
     */
    public String getSessionId() {
        return session_id;
    }

    /**
     * Get the user ID.
     * 
     * @return The user ID
     */
    public Long getUserId() {
        return user_id;
    }

    /**
     * Get the expiration time.
     * 
     * @return The expiration time
     */
    public Long getExpiresAt() {
        return expires_at;
    }
}
