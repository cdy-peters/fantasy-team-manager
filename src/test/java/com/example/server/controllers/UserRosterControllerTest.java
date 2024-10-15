package com.example.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.server.models.ILeaderboardElement;
import com.example.server.models.ISession;
import com.example.server.models.IPlayer;
import com.example.server.models.IUserRoster;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserRosterDAO;

import java.util.Arrays;
import java.util.List;

public class UserRosterControllerTest {

    @Mock
    private SessionDAO sessionDAO;

    @Mock
    private UserRosterDAO userRosterDAO;

    @InjectMocks
    private UserRosterController userRosterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRoster_ValidSessionToken() {
        // Arrange
        String token = "token";
        IUserRoster roster = mock(IUserRoster.class);
        ISession session = mock(ISession.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);

        // Act
        ResponseEntity<?> response = userRosterController.createRoster(token, roster);

        // Assert
        verify(userRosterDAO).createRoster(anyLong(), any(IUserRoster.class));
        assertEquals(ResponseEntity.ok("Roster created successfully for user ID: 1"), response);
    }

    @Test
    void testCreateRoster_InvalidSessionToken() {
        // Arrange
        String token = "invalidToken";
        IUserRoster roster = mock(IUserRoster.class);
        when(sessionDAO.find(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.createRoster(token, roster);

        // Assert
        verify(userRosterDAO, never()).createRoster(anyLong(), any(IUserRoster.class));
        assertEquals(ResponseEntity.status(401).body("Unauthorized"), response);
    }

    @Test
    void testCreateRoster_ExceptionThrown() {
        // Arrange
        String token = "token";
        IUserRoster roster = mock(IUserRoster.class);
        ISession session = mock(ISession.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        doThrow(new RuntimeException()).when(userRosterDAO).createRoster(anyLong(), any(IUserRoster.class));

        // Act
        ResponseEntity<?> response = userRosterController.createRoster(token, roster);

        // Assert
        verify(userRosterDAO).createRoster(anyLong(), any(IUserRoster.class));
        assertEquals(ResponseEntity.status(404).body("Failed to create roster for user ID: 1"), response);
    }

    @Test
    void testGetRoster_ValidSessionToken() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        IUserRoster roster = mock(IUserRoster.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.findRosterByUser(anyLong())).thenReturn(roster);

        // Act
        ResponseEntity<?> response = userRosterController.getRoster(token);

        // Assert
        assertEquals(ResponseEntity.ok(roster), response);
    }

    @Test
    void testGetRoster_InvalidSessionToken() {
        // Arrange
        String token = "invalidToken";
        when(sessionDAO.find(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getRoster(token);

        // Assert
        assertEquals(ResponseEntity.status(401).body("Unauthorized"), response);
    }

    @Test
    void testGetRoster_RosterNotFound() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.findRosterByUser(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getRoster(token);

        // Assert
        assertEquals(ResponseEntity.status(404).body("Roster not found for user ID: 1"), response);
    }

    @Test
    void testGetUserRosterStats_ValidSessionToken() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        ILeaderboardElement roster = mock(ILeaderboardElement.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.getUserRoster(anyLong())).thenReturn(roster);

        // Act
        ResponseEntity<?> response = userRosterController.getUserRosterStats(token);

        // Assert
        assertEquals(ResponseEntity.ok(roster), response);
    }

    @Test
    void testGetUserRosterStats_InvalidSessionToken() {
        // Arrange
        String token = "invalidToken";
        when(sessionDAO.find(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getUserRosterStats(token);

        // Assert
        assertEquals(ResponseEntity.status(401).body("Unauthorized"), response);
    }

    @Test
    void testGetUserRosterStats_RosterNotFound() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.getUserRoster(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getUserRosterStats(token);

        // Assert
        assertEquals(ResponseEntity.status(404).body("Roster not found for user ID: 1"), response);
    }

    @Test
    void testGetRosterPlayers_ValidSessionToken_WithRoster() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        List<IPlayer> roster = Arrays.asList(mock(IPlayer.class), mock(IPlayer.class));
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.findRosterPlayers(anyLong())).thenReturn(roster);

        // Act
        ResponseEntity<?> response = userRosterController.getRosterPlayers(token);

        // Assert
        assertEquals(ResponseEntity.ok(roster), response);
    }

    @Test
    void testGetRosterPlayers_ValidSessionToken_WithoutRoster() {
        // Arrange
        String token = "token";
        ISession session = mock(ISession.class);
        when(session.getUserId()).thenReturn(1L);
        when(sessionDAO.find(anyString())).thenReturn(session);
        when(userRosterDAO.findRosterPlayers(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getRosterPlayers(token);

        // Assert
        assertEquals(ResponseEntity.status(404).body("Roster not found for user ID: 1"), response);
    }

    @Test
    void testGetRosterPlayers_InvalidSessionToken() {
        // Arrange
        String token = "invalidToken";
        when(sessionDAO.find(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = userRosterController.getRosterPlayers(token);

        // Assert
        assertEquals(ResponseEntity.status(401).body("Unauthorized"), response);
    }

    @Test
    void testGetRosters() {
        // Arrange
        List<ILeaderboardElement> rosters = Arrays.asList(mock(ILeaderboardElement.class),
                mock(ILeaderboardElement.class));
        when(userRosterDAO.getRosters()).thenReturn(rosters);

        // Act
        ResponseEntity<?> response = userRosterController.getRosters();

        // Assert
        assertEquals(ResponseEntity.ok(rosters), response);
    }
}
