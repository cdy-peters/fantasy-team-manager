package com.example.interfaces;

/**
 * Interface for the statistics.
 */
public class IStatistics {
    private Long id;
    private String playerName;
    private int gamesPlayed;
    private int starts;
    private String nation;
    private String position;
    private int age;
    private int minutes;
    private int ninetyMinutes;
    private int goals;
    private int assists;
    private int goalsAssists;
    private int goalsPenaltyKicks;
    private int penaltyKicks;
    private int penaltyKickAttempts;
    private int yellowCards;
    private int redCards;
    private double expectedGoals;
    private double nonPenaltyExpectedGoals;
    private double expectedAssists;
    private double npXgPlusXA;
    private int progressiveCarries;
    private int progressivePasses;
    private int progressiveRuns;
    private double goalsPer90;
    private double assistsPer90;
    private double goalsAssistsPer90;
    private double goalsPenaltyKicksPer90;
    private double goalsAssistsPenaltyKicksPer90;
    private double xGPer90;
    private double xAPer90;
    private double xGPlusXAPer90;
    private double npxGPer90;
    private double npxGPlusXAPer90;
    private String team;
    private double playerScore;

    /**
     * Create a new IStatistics instance.
     * 
     * @param id                            The ID
     * @param playerName                    The player name
     * @param gamesPlayed                   The number of games played
     * @param starts                        The number of starts
     * @param nation                        The nation
     * @param position                      The position
     * @param age                           The age
     * @param minutes                       The number of minutes played
     * @param ninetyMinutes                 The number of 90 minutes played
     * @param goals                         The number of goals scored
     * @param assists                       The number of assists
     * @param goalsAssists                  The number of goals and assists
     * @param goalsPenaltyKicks             The number of goals scored from penalty
     *                                      kicks
     * @param penaltyKicks                  The number of penalty kicks taken
     * @param penaltyKickAttempts           The number of penalty kick attempts
     * @param yellowCards                   The number of yellow cards
     * @param redCards                      The number of red cards
     * @param expectedGoals                 The expected goals
     * @param nonPenaltyExpectedGoals       The non-penalty expected goals
     * @param expectedAssists               The expected assists
     * @param npXgPlusXA                    The non-penalty expected goals plus
     *                                      expected assists
     * @param progressiveCarries            The number of progressive carries
     * @param progressivePasses             The number of progressive passes
     * @param progressiveRuns               The number of progressive runs
     * @param goalsPer90                    The goals per 90 minutes
     * @param assistsPer90                  The assists per 90 minutes
     * @param goalsAssistsPer90             The goals and assists per 90 minutes
     * @param goalsPenaltyKicksPer90        The goals scored from penalty kicks per
     *                                      90 minutes
     * @param goalsAssistsPenaltyKicksPer90 The goals and assists from penalty kicks
     *                                      per 90 minutes
     * @param xGPer90                       The expected goals per 90 minutes
     * @param xAPer90                       The expected assists per 90 minutes
     * @param xGPlusXAPer90                 The expected goals plus expected assists
     *                                      per 90 minutes
     * @param npxGPer90                     The non-penalty expected goals per 90
     *                                      minutes
     * @param npxGPlusXAPer90               The non-penalty expected goals plus
     *                                      expected assists per 90 minutes
     * @param team                          The team
     * @param playerScore                   The player score
     */
    public IStatistics(Long id, String playerName, int gamesPlayed, int starts, String nation, String position, int age,
            int minutes,
            int ninetyMinutes, int goals, int assists, int goalsAssists, int goalsPenaltyKicks, int penaltyKicks,
            int penaltyKickAttempts, int yellowCards, int redCards, double expectedGoals,
            double nonPenaltyExpectedGoals,
            double expectedAssists, double npXgPlusXA, int progressiveCarries, int progressivePasses,
            int progressiveRuns,
            double goalsPer90, double assistsPer90, double goalsAssistsPer90, double goalsPenaltyKicksPer90,
            double goalsAssistsPenaltyKicksPer90, double xGPer90, double xAPer90, double xGPlusXAPer90,
            double npxGPer90,
            double npxGPlusXAPer90, String team, double playerScore) {
        this.id = id;
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.starts = starts;
        this.nation = nation;
        this.position = position;
        this.age = age;
        this.minutes = minutes;
        this.ninetyMinutes = ninetyMinutes;
        this.goals = goals;
        this.assists = assists;
        this.goalsAssists = goalsAssists;
        this.goalsPenaltyKicks = goalsPenaltyKicks;
        this.penaltyKicks = penaltyKicks;
        this.penaltyKickAttempts = penaltyKickAttempts;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.expectedGoals = expectedGoals;
        this.nonPenaltyExpectedGoals = nonPenaltyExpectedGoals;
        this.expectedAssists = expectedAssists;
        this.npXgPlusXA = npXgPlusXA;
        this.progressiveCarries = progressiveCarries;
        this.progressivePasses = progressivePasses;
        this.progressiveRuns = progressiveRuns;
        this.goalsPer90 = goalsPer90;
        this.assistsPer90 = assistsPer90;
        this.goalsAssistsPer90 = goalsAssistsPer90;
        this.goalsPenaltyKicksPer90 = goalsPenaltyKicksPer90;
        this.goalsAssistsPenaltyKicksPer90 = goalsAssistsPenaltyKicksPer90;
        this.xGPer90 = xGPer90;
        this.xAPer90 = xAPer90;
        this.xGPlusXAPer90 = xGPlusXAPer90;
        this.npxGPer90 = npxGPer90;
        this.npxGPlusXAPer90 = npxGPlusXAPer90;
        this.team = team;
        this.playerScore = playerScore;
    }

    // Add getter methods for all the fields
    /**
     * Get the ID of the statistics.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the player name.
     * 
     * @return The player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Get the number of games played.
     * 
     * @return The number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Get the number of starts.
     * 
     * @return The number of starts
     */
    public int getStarts() {
        return starts;
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
     * Get the age of the player.
     * 
     * @return The age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the number of minutes played.
     * 
     * @return The number of minutes played
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Get the number of 90 minutes played.
     * 
     * @return The number of 90 minutes played
     */
    public int getNinetyMinutes() {
        return ninetyMinutes;
    }

    /**
     * Get the number of goals scored.
     * 
     * @return The number of goals scored
     */
    public int getGoals() {
        return goals;
    }

    /**
     * Get the number of assists.
     * 
     * @return The number of assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * Get the number of goals and assists.
     * 
     * @return The number of goals and assists
     */
    public int getGoalsAssists() {
        return goalsAssists;
    }

    /**
     * Get the number of goals scored from penalty kicks.
     * 
     * @return The number of goals scored from penalty kicks
     */
    public int getGoalsPenaltyKicks() {
        return goalsPenaltyKicks;
    }

    /**
     * Get the number of penalty kicks taken.
     * 
     * @return The number of penalty kicks taken
     */
    public int getPenaltyKicks() {
        return penaltyKicks;
    }

    /**
     * Get the number of penalty kick attempts.
     * 
     * @return The number of penalty kick attempts
     */
    public int getPenaltyKickAttempts() {
        return penaltyKickAttempts;
    }

    /**
     * Get the number of yellow cards.
     * 
     * @return The number of yellow cards
     */
    public int getYellowCards() {
        return yellowCards;
    }

    /**
     * Get the number of red cards.
     * 
     * @return The number of red cards
     */
    public int getRedCards() {
        return redCards;
    }

    /**
     * Get the expected goals.
     * 
     * @return The expected goals
     */
    public double getExpectedGoals() {
        return expectedGoals;
    }

    /**
     * Get the expected assists.
     * 
     * @return The expected assists
     */
    public double getNonPenaltyExpectedGoals() {
        return nonPenaltyExpectedGoals;
    }

    /**
     * Get the expected assists.
     * 
     * @return The expected assists
     */
    public double getExpectedAssists() {
        return expectedAssists;
    }

    /**
     * Get the non-penalty expected goals plus expected assists.
     * 
     * @return The non-penalty expected goals plus expected assists
     */
    public double getNpXgPlusXA() {
        return npXgPlusXA;
    }

    /**
     * Get the number of progressive carries.
     * 
     * @return The number of progressive carries
     */
    public int getProgressiveCarries() {
        return progressiveCarries;
    }

    /**
     * Get the number of progressive passes.
     * 
     * @return The number of progressive passes
     */
    public int getProgressivePasses() {
        return progressivePasses;
    }

    /**
     * Get the number of progressive runs.
     * 
     * @return The number of progressive runs
     */
    public int getProgressiveRuns() {
        return progressiveRuns;
    }

    /**
     * Get the goals per 90 minutes.
     * 
     * @return The goals per 90 minutes
     */
    public double getGoalsPer90() {
        return goalsPer90;
    }

    /**
     * Get the assists per 90 minutes.
     * 
     * @return The assists per 90 minutes
     */
    public double getAssistsPer90() {
        return assistsPer90;
    }

    /**
     * Get the goals and assists per 90 minutes.
     * 
     * @return The goals and assists per 90 minutes
     */
    public double getGoalsAssistsPer90() {
        return goalsAssistsPer90;
    }

    /**
     * Get the goals scored from penalty kicks per 90 minutes.
     * 
     * @return The goals scored from penalty kicks per 90 minutes
     */
    public double getGoalsPenaltyKicksPer90() {
        return goalsPenaltyKicksPer90;
    }

    /**
     * Get the goals and assists from penalty kicks per 90 minutes.
     * 
     * @return The goals and assists from penalty kicks per 90 minutes
     */
    public double getGoalsAssistsPenaltyKicksPer90() {
        return goalsAssistsPenaltyKicksPer90;
    }

    /**
     * Get the expected goals per 90 minutes.
     * 
     * @return The expected goals per 90 minutes
     */
    public double getxGPer90() {
        return xGPer90;
    }

    /**
     * Get the expected assists per 90 minutes.
     * 
     * @return The expected assists per 90 minutes
     */
    public double getxAPer90() {
        return xAPer90;
    }

    /**
     * Get the expected goals plus expected assists per 90 minutes.
     * 
     * @return The expected goals plus expected assists per 90 minutes
     */
    public double getxGPlusXAPer90() {
        return xGPlusXAPer90;
    }

    /**
     * Get the non-penalty expected goals per 90 minutes.
     * 
     * @return The non-penalty expected goals per 90 minutes
     */
    public double getNpxGPer90() {
        return npxGPer90;
    }

    /**
     * Get the non-penalty expected goals plus expected assists per 90 minutes.
     * 
     * @return The non-penalty expected goals plus expected assists per 90 minutes
     */
    public double getNpxGPlusXAPer90() {
        return npxGPlusXAPer90;
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
     * Get the player score.
     * 
     * @return The player score
     */
    public int getPlayerScore() {
        return (int) playerScore;
    }
}