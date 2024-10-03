package com.example.server.models;

import java.util.HashMap;
import java.util.Map;

public class IPlayer {
    private Long id;
    private String name;
    private String nation;
    private String position;
    private String team;
    private int score;

    public IPlayer(Long id, String name, String nation, String position, String team, int score) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.position = position;
        this.team = team;
        this.score = score;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public int getScore() {
        return score;
    }

    public Map<String, Integer> getRosterValue() {
        Map<String, Integer> rosterValue = new HashMap<>();
        rosterValue.put("id", id.intValue());
        rosterValue.put("score", score);
        return rosterValue;
    }
}
