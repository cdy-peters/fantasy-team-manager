package com.example.server.models;

public class IStatistics {
    private Long id;  // Unique identifier for the statistics entry
    private String player_name;  // Name of the player
    private int goals;  // Number of goals scored by the player
    private int assists;  // Number of assists by the player
    private int games_played;  // Number of games played by the player
    private int starts;  // Number of games started by the player   

    public IStatistics(Long id, String player_name, int goals, int assists, int games_played, int starts) {
        this.id = id;
        this.player_name = player_name;
        this.goals = goals;
        this.assists = assists;
        this.games_played = games_played;
        this.starts = starts;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return player_name;
    }

    public void setPlayerName(String playerId) {
        this.player_name = player_name;
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

    public int getGamesPlayed() {
        return games_played;
    }

    public void setGamesPlayed(int games_played) {
        this.games_played = games_played;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }   

}