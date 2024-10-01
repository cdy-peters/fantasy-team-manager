package com.example.client.helpers;

public record Statistics(
    Long id, String playerName, int gamesPlayed, int starts, String nation, String position, int age, int minutes,
    int ninetyMinutes, int goals, int assists, int goalsAssists, int goalsPenaltyKicks, int penaltyKicks, 
    int penaltyKickAttempts, int yellowCards, int redCards, double expectedGoals, double nonPenaltyExpectedGoals, 
    double expectedAssists, double npXgPlusXA, int progressiveCarries, int progressivePasses, int progressiveRuns, 
    double goalsPer90, double assistsPer90, double goalsAssistsPer90, double goalsPenaltyKicksPer90, 
    double goalsAssistsPenaltyKicksPer90, double xGPer90, double xAPer90, double xGPlusXAPer90, double npxGPer90, 
    double npxGPlusXAPer90, String team
) {
	
}
