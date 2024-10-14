package com.example.server.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.server.models.ISession;
import com.example.server.models.IUser;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;

public class UserControllerTest {

        @Mock
        private UserDAO userDAO;

        @Mock
        private SessionDAO sessionDAO;

        @Spy
        @InjectMocks
        private UserController userController;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCreateSession_Failure() throws Exception {
                Long userId = 1L;

                // Arrange
                when(sessionDAO.create(anyLong())).thenThrow(new Exception());

                // Act
                ResponseEntity<?> response = userController.createSession(userId);

                // Assert
                assertEquals(500, response.getStatusCode().value());
                assertEquals("Failed to create session", response.getBody());
        }

        @Test
        public void testCreateSession_Success() throws Exception {
                Long userId = 1L;
                String token = "token";

                // Arrange
                ISession session = mock(ISession.class);
                when(session.getSessionId()).thenReturn(token);

                when(sessionDAO.create(anyLong())).thenReturn(session);

                // Act
                ResponseEntity<?> response = userController.createSession(userId);

                // Assert
                assertEquals(201, response.getStatusCode().value());
                assertEquals("Session created", response.getBody());
                assertEquals("token", response.getHeaders().get("Authorization").get(0));
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

        // Empty fields
        @Test
        public void testRegister_NameEmpty() throws Exception {
                String name = "";
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing name", response.getBody());
        }

        @Test
        public void testRegister_EmailEmpty() throws Exception {
                String name = "name";
                String email = "";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing email", response.getBody());
        }

        @Test
        public void testRegister_UsernameEmpty() throws Exception {
                String name = "name";
                String email = "email@test.com";
                String username = "";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing username", response.getBody());
        }

        @Test
        public void testRegister_PasswordEmpty() throws Exception {
                String name = "name";
                String email = "email@test.com";
                String username = "username";
                String password = "";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(400, response.getStatusCode().value());
                assertEquals("Missing password", response.getBody());
        }

        // Invalid fields
        @Test
        public void testRegister_InvalidName_Numbers() throws Exception {
                String name = "name123";
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid name", response.getBody());
        }

        @Test
        public void testRegister_InvalidName_SpecialCharacters() throws Exception {
                String name = "name!";
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid name", response.getBody());
        }

        @Test
        public void testRegister_InvalidName_TooShort() throws Exception {
                String name = "n";
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid name", response.getBody());
        }

        @Test
        public void testRegister_InvalidName_TooLong() throws Exception {
                String name = "n".repeat(260);
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid name", response.getBody());
        }

        @Test
        public void testRegister_InvalidEmail_NoAtSymbol() throws Exception {
                String name = "name";
                String email = "emailtest.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid email", response.getBody());
        }

        @Test
        public void testRegister_InvalidEmail_NoDomain() throws Exception {
                String name = "name";
                String email = "email@";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid email", response.getBody());
        }

        @Test
        public void testRegister_InvalidEmail_NoTLD() throws Exception {
                String name = "name";
                String email = "email@test";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid email", response.getBody());
        }

        @Test
        public void testRegister_InvalidUsername_TooShort() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "u";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid username", response.getBody());
        }

        @Test
        public void testRegister_InvalidUsername_TooLong() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "u".repeat(21);
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid username", response.getBody());
        }

        @Test
        public void testRegister_InvalidUsername_SpecialCharacters() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "u$ername";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid username", response.getBody());
        }

        @Test
        public void testRegister_InvalidPassword_TooShort() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "username";
                String password = "p";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid password", response.getBody());
        }

        @Test
        public void testRegister_InvalidPassword_NoUppercase() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "username";
                String password = "password1";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid password", response.getBody());
        }

        @Test
        public void testRegister_InvalidPassword_NoLowercase() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "username";
                String password = "PASSWORD1";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid password", response.getBody());
        }

        @Test
        public void testRegister_InvalidPassword_NoNumber() throws Exception {
                String name = "name";
                String email = "test@email.com";
                String username = "username";
                String password = "Password";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(422, response.getStatusCode().value());
                assertEquals("Invalid password", response.getBody());
        }

        // User creation
        @Test
        public void testRegister_UserExists() throws Exception {
                String name = "name";
                String email = "email@test.com";
                String username = "username";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Throw SQLIntegrityConstraintViolationException
                doThrow(SQLIntegrityConstraintViolationException.class).when(userDAO).create(user);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(409, response.getStatusCode().value());
                assertEquals("Username or email already exists", response.getBody());
        }

        @Test
        public void testRegister_FailedToCreateUser() throws Exception {
                String name = "name";
                String email = "email@test.com";
                String username = "takenUsername";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Throw SQLIntegrityConstraintViolationException
                doThrow(Exception.class).when(userDAO).create(user);

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(500, response.getStatusCode().value());
                assertEquals("Failed to create user", response.getBody());
        }

        @Test
        public void testRegister_Success() throws Exception {
                String name = "name";
                String email = "email@test.com";
                String username = "takenUsername";
                String password = "Password123";

                // Arrange
                IUser user = mock(IUser.class);
                when(user.getName()).thenReturn(name);
                when(user.getEmail()).thenReturn(email);
                when(user.getUsername()).thenReturn(username);
                when(user.getPassword()).thenReturn(password);

                // Create user
                when(userDAO.create(user)).thenReturn(1L);

                ResponseEntity<?> sessionResponse = ResponseEntity.status(201)
                                .header("Authorization", "token")
                                .body("Session created");
                doReturn(sessionResponse).when(userController).createSession(anyLong());

                // Act
                ResponseEntity<?> response = userController.register(user);

                // Assert
                assertEquals(201, response.getStatusCode().value());
                assertEquals("Session created", response.getBody());
                assertEquals("token", response.getHeaders().get("Authorization").get(0));
        }

        @Test
        public void testLogout_SessionNotFound() throws Exception {
                // Arrange
                when(sessionDAO.find(anyString())).thenReturn(null);

                // Act
                ResponseEntity<?> response = userController.logout("token");

                // Assert
                assertEquals(205, response.getStatusCode().value());
                assertEquals("Session not found", response.getBody());
        }

        @Test
        public void testLogout_FailedToLogout() throws Exception {
                // Arrange
                ISession session = mock(ISession.class);
                when(session.getSessionId()).thenReturn("token");

                when(sessionDAO.find(anyString())).thenReturn(session);
                doThrow(SQLException.class).when(sessionDAO).delete(anyString());

                // Act
                ResponseEntity<?> response = userController.logout("token");

                // Assert
                assertEquals(500, response.getStatusCode().value());
                assertEquals("Failed to logout", response.getBody());
        }

        @Test
        public void testLogout_Success() throws Exception {
                // Arrange
                ISession session = mock(ISession.class);
                when(session.getSessionId()).thenReturn("token");

                when(sessionDAO.find(anyString())).thenReturn(session);

                // Act
                ResponseEntity<?> response = userController.logout("token");

                // Assert
                assertEquals(200, response.getStatusCode().value());
                assertEquals("Logged out", response.getBody());
        }
}
