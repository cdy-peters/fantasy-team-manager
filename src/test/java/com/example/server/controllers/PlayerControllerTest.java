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

import com.example.interfaces.IPlayer;
import com.example.server.models.PlayerDAO;

import java.util.Arrays;
import java.util.List;

public class PlayerControllerTest {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlayers_NullPosition_WithPlayers() {
        // Arrange
        String position = null;
        List<IPlayer> playersList = Arrays.asList(mock(IPlayer.class), mock(IPlayer.class));
        when(playerDAO.findAllPlayers()).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.ok(playersList), response);
    }

    @Test
    void testGetPlayers_NullPosition_NoPlayers() {
        // Arrange
        String position = null;
        List<IPlayer> playersList = Arrays.asList();
        when(playerDAO.findAllPlayers()).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

    @Test
    void testGetPlayers_NullPosition_NullPlayers() {
        // Arrange
        String position = null;
        when(playerDAO.findAllPlayers()).thenReturn(null);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

    @Test
    void testGetPlayers_EmptyPosition_WithPlayers() {
        // Arrange
        String position = "";
        List<IPlayer> playersList = Arrays.asList(mock(IPlayer.class), mock(IPlayer.class));
        when(playerDAO.findAllPlayers()).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.ok(playersList), response);
    }

    @Test
    void testGetPlayers_EmptyPosition_NoPlayers() {
        // Arrange
        String position = "";
        List<IPlayer> playersList = Arrays.asList();
        when(playerDAO.findAllPlayers()).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

    @Test
    void testGetPlayers_EmptyPosition_NullPlayers() {
        // Arrange
        String position = "";
        when(playerDAO.findAllPlayers()).thenReturn(null);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

    @Test
    void testGetPlayers_WithPosition_WithPlayers() {
        // Arrange
        String position = "MF";
        List<IPlayer> playersList = Arrays.asList(mock(IPlayer.class), mock(IPlayer.class));
        when(playerDAO.findPlayersByPosition(anyString())).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.ok(playersList), response);
    }

    @Test
    void testGetPlayers_WithPosition_NoPlayers() {
        // Arrange
        String position = "MF";
        List<IPlayer> playersList = Arrays.asList();
        when(playerDAO.findPlayersByPosition(anyString())).thenReturn(playersList);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

    @Test
    void testGetPlayers_WithPosition_NullPlayers() {
        // Arrange
        String position = "MF";
        when(playerDAO.findPlayersByPosition(anyString())).thenReturn(null);

        // Act
        ResponseEntity<?> response = playerController.getPlayers(position);

        // Assert
        assertEquals(ResponseEntity.status(404).body("No players found"), response);
    }

}
