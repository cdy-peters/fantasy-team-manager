package com.example.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.server.models.IStatistics;
import com.example.server.models.PlayerStatisticsDAO;

import java.util.Arrays;
import java.util.List;

public class StatisticsControllerTest {

    @Mock
    private PlayerStatisticsDAO playerStatisticsDAO;

    @InjectMocks
    private StatisticsController statisticsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlayerStatistics_NullPlayerId_WithStatistics() {
        // Arrange
        String playerId = null;
        List<IStatistics> statisticsList = Arrays.asList(mock(IStatistics.class), mock(IStatistics.class));
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.ok(statisticsList), response);
    }

    @Test
    void testGetPlayerStatistics_NullPlayerId_NoStatistics() {
        // Arrange
        String playerId = null;
        List<IStatistics> statisticsList = Arrays.asList();
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }

    @Test
    void testGetPlayerStatistics_NullPlayerId_NullStatistics() {
        // Arrange
        String playerId = null;
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(null);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }

    @Test
    void testGetPlayerStatistics_EmptyPlayerId_WithStatistics() {
        // Arrange
        String playerId = "";
        List<IStatistics> statisticsList = Arrays.asList(mock(IStatistics.class), mock(IStatistics.class));
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.ok(statisticsList), response);
    }

    @Test
    void testGetPlayerStatistics_EmptyPlayerId_NoStatistics() {
        // Arrange
        String playerId = "";
        List<IStatistics> statisticsList = Arrays.asList();
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }

    @Test
    void testGetPlayerStatistics_EmptyPlayerId_NullStatistics() {
        // Arrange
        String playerId = "";
        when(playerStatisticsDAO.findAllStatistics()).thenReturn(null);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }

    @Test
    void testGetPlayerStatistics_PlayerId_WithStatistics() {
        // Arrange
        String playerId = "1";
        List<IStatistics> statisticsList = Arrays.asList(mock(IStatistics.class), mock(IStatistics.class));
        when(playerStatisticsDAO.findByPlayerId(anyString())).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.ok(statisticsList), response);
    }

    @Test
    void testGetPlayerStatistics_PlayerId_NoStatistics() {
        // Arrange
        String playerId = "1";
        List<IStatistics> statisticsList = Arrays.asList();
        when(playerStatisticsDAO.findByPlayerId(anyString())).thenReturn(statisticsList);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }

    @Test
    void testGetPlayerStatistics_PlayerId_NullStatistics() {
        // Arrange
        String playerId = "1";
        when(playerStatisticsDAO.findByPlayerId(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = statisticsController.getPlayerStatistics(playerId);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No statistics found"), response);
    }
}
