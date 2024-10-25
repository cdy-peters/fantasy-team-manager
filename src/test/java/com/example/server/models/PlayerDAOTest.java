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

import com.example.interfaces.IPlayer;
import com.example.server.Server;

public class PlayerDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private PlayerDAO playerDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        Server.conn = mockConnection;
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    void testFindPlayersByPosition_WithResults() throws SQLException {
        // Arrange
        String position = "MF";
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getLong("id")).thenReturn(1L, 2L);

        // Act
        List<IPlayer> actualPlayers = playerDAO.findPlayersByPosition(position);

        // Assert
        assertNotNull(actualPlayers);
        assertEquals(2, actualPlayers.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindPlayersByPosition_NoResults() throws SQLException {
        // Arrange
        String position = "MF";
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<IPlayer> actualPlayers = playerDAO.findPlayersByPosition(position);

        // Assert
        assertNotNull(actualPlayers);
        assertEquals(0, actualPlayers.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindAllPlayers_WithResults() throws SQLException {
        // Arrange
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getLong("id")).thenReturn(1L, 2L);

        // Act
        List<IPlayer> actualPlayers = playerDAO.findAllPlayers();

        // Assert
        assertNotNull(actualPlayers);
        assertEquals(2, actualPlayers.size());
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    void testFindAllPlayers_NoResults() throws SQLException {
        // Arrange
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<IPlayer> actualPlayers = playerDAO.findAllPlayers();

        // Assert
        assertNotNull(actualPlayers);
        assertEquals(0, actualPlayers.size());
        verify(mockStatement).executeQuery(anyString());
    }
}
