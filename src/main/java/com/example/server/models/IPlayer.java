package com.example.server.models;

public class IPlayer {
    private Long id;
    private String name;
    private String nation;
    private String position;
    private String team;

    public IPlayer(Long id, String name, String nation, String position, String team) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.position = position;
        this.team = team;
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
}
