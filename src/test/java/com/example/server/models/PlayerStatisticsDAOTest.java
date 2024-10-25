package com.example.server.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.interfaces.IStatistics;
import com.example.server.Server;

public class PlayerStatisticsDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private PlayerStatisticsDAO playerStatisticsDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        Server.conn = mockConnection;
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    void testFindByPlayerId_WithResults() throws SQLException {
        // Arrange
        String playerId = "1";
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getLong("id")).thenReturn(1L, 2L);

        // Act
        List<IStatistics> actualStatistics = playerStatisticsDAO.findByPlayerId(playerId);

        // Assert
        assertNotNull(actualStatistics);
        assertEquals(2, actualStatistics.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindByPlayerId_NoResults() throws SQLException {
        // Arrange
        String playerId = "1";
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<IStatistics> actualStatistics = playerStatisticsDAO.findByPlayerId(playerId);

        // Assert
        assertNotNull(actualStatistics);
        assertEquals(0, actualStatistics.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindAllStatistics_WithResults() throws SQLException {
        // Arrange
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getLong("id")).thenReturn(1L, 2L);

        // Act
        List<IStatistics> actualStatistics = playerStatisticsDAO.findAllStatistics();

        // Assert
        assertNotNull(actualStatistics);
        assertEquals(2, actualStatistics.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindAllStatistics_NoResults() throws SQLException {
        // Arrange
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<IStatistics> actualStatistics = playerStatisticsDAO.findAllStatistics();

        // Assert
        assertNotNull(actualStatistics);
        assertEquals(0, actualStatistics.size());
        verify(mockStatement).executeQuery(anyString());
    }
}
