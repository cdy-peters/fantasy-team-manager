package com.example.server.models;

public class IPlayer {
    private int id;
    private String name;

    public IPlayer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
