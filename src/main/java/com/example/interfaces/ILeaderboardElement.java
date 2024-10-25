package com.example.interfaces;

/**
 * Interface for the leaderboard element.
 */
public class ILeaderboardElement {
    private Long id;
    private int rank;
    private Long userId;
    private String username;
    private int score;
    private float cost;

    /**
     * Create a new ILeaderboardElement instance.
     * 
     * @param id       The ID
     * @param rank     The rank
     * @param userId   The user ID
     * @param username The username
     * @param score    The score
     * @param cost     The cost
     */
    public ILeaderboardElement(Long id, int rank, Long userId, String username, int score, float cost) {
        this.id = id;
        this.rank = rank;
        this.userId = userId;
        this.username = username;
        this.score = score;
        this.cost = cost;
    }

    /**
     * Get the ID of the leaderboard element.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the rank of the leaderboard element.
     * 
     * @return The rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Get the user ID of the leaderboard element.
     * 
     * @return The user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Get the username of the leaderboard element.
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the score of the leaderboard element.
     * 
     * @return The score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the cost of the leaderboard element.
     * 
     * @return The cost
     */
    public float getCost() {
        return cost;
    }

}
