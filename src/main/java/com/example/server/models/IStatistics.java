package com.example.server.models;

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

    public IStatistics(Long id, String playerName, int gamesPlayed, int starts, String nation, String position, int age, int minutes,
                       int ninetyMinutes, int goals, int assists, int goalsAssists, int goalsPenaltyKicks, int penaltyKicks, 
                       int penaltyKickAttempts, int yellowCards, int redCards, double expectedGoals, double nonPenaltyExpectedGoals, 
                       double expectedAssists, double npXgPlusXA, int progressiveCarries, int progressivePasses, int progressiveRuns, 
                       double goalsPer90, double assistsPer90, double goalsAssistsPer90, double goalsPenaltyKicksPer90, 
                       double goalsAssistsPenaltyKicksPer90, double xGPer90, double xAPer90, double xGPlusXAPer90, double npxGPer90, 
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
    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getStarts() {
        return starts;
    }

    public String getNation() {
        return nation;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getNinetyMinutes() {
        return ninetyMinutes;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoalsAssists() {
        return goalsAssists;
    }

    public int getGoalsPenaltyKicks() {
        return goalsPenaltyKicks;
    }

    public int getPenaltyKicks() {
        return penaltyKicks;
    }

    public int getPenaltyKickAttempts() {
        return penaltyKickAttempts;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public double getExpectedGoals() {
        return expectedGoals;
    }

    public double getNonPenaltyExpectedGoals() {
        return nonPenaltyExpectedGoals;
    }

    public double getExpectedAssists() {
        return expectedAssists;
    }

    public double getNpXgPlusXA() {
        return npXgPlusXA;
    }

    public int getProgressiveCarries() {
        return progressiveCarries;
    }

    public int getProgressivePasses() {
        return progressivePasses;
    }

    public int getProgressiveRuns() {
        return progressiveRuns;
    }

    public double getGoalsPer90() {
        return goalsPer90;
    }

    public double getAssistsPer90() {
        return assistsPer90;
    }

    public double getGoalsAssistsPer90() {
        return goalsAssistsPer90;
    }

    public double getGoalsPenaltyKicksPer90() {
        return goalsPenaltyKicksPer90;
    }

    public double getGoalsAssistsPenaltyKicksPer90() {
        return goalsAssistsPenaltyKicksPer90;
    }

    public double getxGPer90() {
        return xGPer90;
    }

    public double getxAPer90() {
        return xAPer90;
    }

    public double getxGPlusXAPer90() {
        return xGPlusXAPer90;
    }

    public double getNpxGPer90() {
        return npxGPer90;
    }

    public double getNpxGPlusXAPer90() {
        return npxGPlusXAPer90;
    }

    public String getTeam() {
        return team;
    }

    public double getPlayerScore() {
        return playerScore;
    }
}