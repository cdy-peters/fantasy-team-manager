package com.example.interfaces;

/**
 * Interface for the player.
 */
public class IPlayer {
    private Long id;
    private String name;
    private String nation;
    private String position;
    private String team;
    private int score;
    private double price;

    /**
     * Create a new IPlayer instance.
     * 
     * @param id       The ID
     * @param name     The name
     * @param nation   The nation
     * @param position The position
     * @param team     The team
     * @param score    The score
     * @param price    The price
     */
    public IPlayer(Long id, String name, String nation, String position, String team, int score, double price) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.position = position;
        this.team = team;
        this.score = score;
        this.price = price;
    }

    // Getters
    /**
     * Get the ID of the player.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the player.
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the nation of the player.
     * 
     * @return The nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * Get the position of the player.
     * 
     * @return The position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Get the team of the player.
     * 
     * @return The team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Get the score of the player.
     * 
     * @return The score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the price of the player.
     * 
     * @return The price
     */
    public double getPrice() {
        return price;
    }
}
