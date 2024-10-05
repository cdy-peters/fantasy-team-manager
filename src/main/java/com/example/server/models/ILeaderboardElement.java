package com.example.server.models;

public class ILeaderboardElement {
    private Long id;
    private Long userId;
    private String username;
    private int score;

    public ILeaderboardElement(Long id, Long userId, String username, int score) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername(){
        return username;
    }

    public int getScore() {
        return score;
    }

}
