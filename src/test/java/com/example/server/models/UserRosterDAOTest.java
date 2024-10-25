package com.example.server.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.interfaces.ILeaderboardElement;
import com.example.interfaces.IPlayer;
import com.example.interfaces.IUserRoster;

public class UserRosterDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserRosterDAO userRosterDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindRosterByUser_NotFound() throws Exception {
        Long userId = 1L;

        // Arrange
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act
        IUserRoster roster = userRosterDAO.findRosterByUser(userId);

        // Assert
        assertNull(roster);
    }



    @Test
    public void testGetRosters_Empty() throws Exception {
        // Arrange
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act
        List<ILeaderboardElement> rosters = userRosterDAO.getRosters();

        // Assert
        assertNotNull(rosters);
        assertTrue(rosters.isEmpty());
    }



    @Test
    public void testGetUserRoster_NotFound() throws Exception {
        Long userId = 1L;

        // Arrange
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act
        ILeaderboardElement roster = userRosterDAO.getUserRoster(userId);

        // Assert
        assertNull(roster);
    }

    @Test
    public void testFindRosterPlayers_Empty() throws Exception {
        Long userId = 1L;

        // Arrange
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act
        List<IPlayer> players = userRosterDAO.findRosterPlayers(userId);

        // Assert
        assertNotNull(players);
        assertTrue(players.isEmpty());
    }
}
