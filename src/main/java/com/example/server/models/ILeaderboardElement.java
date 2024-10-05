package com.example.server.models;

public class ILeaderboardElement {
    private Long id;
    private int rank;
    private Long userId;
    private String username;
    private int score;

    public ILeaderboardElement(Long id, int rank, Long userId, String username, int score) {
        this.id = id;
        this.rank = rank;
        this.userId = userId;
        this.username = username;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

}
