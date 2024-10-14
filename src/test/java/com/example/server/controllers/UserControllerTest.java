package com.example.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.server.models.IUser;
import com.example.server.models.UserDAO;

public class UserControllerTest {

        @Mock
        private UserDAO userDAO;

        @Spy
        @InjectMocks
        private UserController userController;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testLogin_UsernameEmpty() throws Exception {
                String username = "";
                String password = "password";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.login(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing username", response.getBody());
        }

        @Test
        public void testLogin_PasswordEmpty() throws Exception {
                String username = "username";
                String password = "";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.login(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing password", response.getBody());
        }

        @Test
        public void testLogin_UserNotFound() throws Exception {
                String username = "nonExistentUser";
                String password = "password";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                when(userDAO.findByUsername(anyString())).thenReturn(null);

                // Act
                ResponseEntity<?> response = userController.login(user);

                // Assert
                assertEquals(401, response.getStatusCode().value());
                assertEquals("Incorrect credentials", response.getBody());
        }

        @Test
        public void testLogin_PasswordIncorrect() throws Exception {
                String username = "username";
                String password = "incorrectPassword";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                IUser existingUser = mock(IUser.class);
                when(existingUser.getPassword()).thenReturn("hashedPassword");

                when(userDAO.findByUsername(anyString())).thenReturn(existingUser);

                // Act
                ResponseEntity<?> response = userController.login(user);

                // Assert
                assertEquals(401, response.getStatusCode().value());
                assertEquals("Incorrect credentials", response.getBody());
        }

        @Test
        public void testLogin_Success() throws Exception {
                String username = "username";
                String password = "password";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                IUser existingUser = mock(IUser.class);
                String hashedPassword = new BCryptPasswordEncoder().encode(password);
                when(existingUser.getPassword()).thenReturn(hashedPassword);
                when(existingUser.getId()).thenReturn(1L);

                when(userDAO.findByUsername(anyString())).thenReturn(existingUser);

                ResponseEntity<?> sessionResponse = ResponseEntity.status(201)
                                .header("Authorization", "token")
                                .body("Session created");
                doReturn(sessionResponse).when(userController).createSession(anyLong());

                // Act
                ResponseEntity<?> response = userController.login(user);

                // Assert
                assertEquals(201, response.getStatusCode().value());
                assertEquals("Session created", response.getBody());
                assertEquals("token", response.getHeaders().get("Authorization").get(0));
        }
}
