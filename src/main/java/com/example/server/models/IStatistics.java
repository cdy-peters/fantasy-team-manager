package com.example.server.models;

public class IStatistics {
    private Long id;  // Unique identifier for the statistics entry
    private String playerId;  // ID of the player
    private int goals;  // Number of goals scored by the player
    private int assists;  // Number of assists by the player

    public IStatistics(Long id, String playerId, int goals, int assists) {
        this.id = id;
        this.playerId = playerId;
        this.goals = goals;
        this.assists = assists;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
}