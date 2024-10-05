package com.example.server.models;

public class ILeaderboardElement {
    private Long id;
    private Long userId;
    private int score;

    public ILeaderboardElement(Long id, Long userId, int score) {
        this.id = id;
        this.userId = userId;
        this.score = score;
    }

    public Long getID() {
        return id;
    }

    public Long getUserID() {
        return userId;
    }
    
    public int getScore() {
        return score;
    }

}
