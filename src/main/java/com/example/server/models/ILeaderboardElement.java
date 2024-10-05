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

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

}
