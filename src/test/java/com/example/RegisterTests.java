package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.client.controllers.RegisterController;
import com.example.server.models.IUser;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;
import com.example.utils.JavaFXInitializer;
import com.example.utils.ReflectionUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterTests {
        @Mock
        private UserDAO mockUserDAO;

        @Mock
        private SessionDAO mockSessionDAO;

        @Mock
        private HttpClient mockHttpClient;

        @Mock
        private HttpResponse<String> mockResponse;

        @Mock
        private Stage mockStage;

        @Mock
        private FXMLLoader mockFxmlLoader;

        @InjectMocks
        private RegisterController registerController;

        @BeforeEach
        public void setUp() {
                JavaFXInitializer.initialize(); // Initialize JavaFX toolkit
                MockitoAnnotations.openMocks(this);
                // Set up mocks for the JavaFX components if needed
                // Example: when(mockStage.getScene()).thenReturn(new Scene(new Group(), 800,
                // 600));
        }

        private String setUpMockFields(String name, String email, String username, String password) throws Exception {
                // Use reflection to access and set private fields
                setPrivateField("nameField", new TextField(name));
                setPrivateField("emailField", new TextField(email));
                setPrivateField("usernameField", new TextField(username));
                PasswordField passwordField = new PasswordField();
                passwordField.setText(password);
                setPrivateField("passwordField", passwordField);

                // Create HTTP body
                return String.format(
                                "{\"name\": \"%s\", \"email\": \"%s\", \"username\": \"%s\", \"password\": \"%s\"}",
                                name, email, username, password);
        }

        private void invokePrivateMethod(String methodName) throws Exception {
                ReflectionUtils.invokePrivateMethod(registerController, methodName, new Class<?>[] {});
        }

        private Object getPrivateField(String fieldName) throws Exception {
                return ReflectionUtils.getPrivateField(registerController, fieldName);
        }

        private void setPrivateField(String fieldName, Object value) throws Exception {
                Field field = RegisterController.class.getDeclaredField(fieldName);
                field.setAccessible(true);

        }

        @Test
        public void testSuccessfulRequest() throws Exception {
                HttpHeaders headers = HttpHeaders.of(
                                Map.of("Set-Cookie", List.of("sessionCookieValue")),
                                (name, value) -> true);

                // Setup mock response
                HttpResponse<String> mockResponse = mock(HttpResponse.class);
                when(mockResponse.statusCode()).thenReturn(201);
                when(mockResponse.headers()).thenReturn(headers);
                when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                                .thenReturn(mockResponse);

                // Set up the controller fields and create the HTTP body
                String body = setUpMockFields("John Doe", "john@example.com", "john_doe", "");

                // Call the createHttpRequest method using reflection
                HttpRequest request = ReflectionUtils.callCreateHttpRequest(registerController, body);

                // Send the request
                HttpResponse<String> response = mockHttpClient.send(request, HttpResponse.BodyHandlers.ofString());

                // Handle the response
                ReflectionUtils.callHandleResponse(registerController, response);

                // Verify HttpClient interaction
                verify(mockHttpClient).send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class));
        }

        @Test
        public void testSuccessfulRegistration() throws Exception {
                // Set up the controller fields and create the HTTP body
                String body = setUpMockFields("John Doe", "john@example.com", "john_doe", "Password123!");

                // Mock the HTTP response
                HttpHeaders headers = HttpHeaders.of(
                                Map.of("Set-Cookie", List.of("sessionCookieValue")),
                                (name, value) -> true);
                when(mockResponse.statusCode()).thenReturn(201);
                when(mockResponse.headers()).thenReturn(headers);
                when(mockResponse.body()).thenReturn("Success");

                // Mock the HttpClient to return the mockResponse
                when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                                .thenReturn(mockResponse);

                // Call createHttpRequest method using reflection
                HttpRequest request = ReflectionUtils.callCreateHttpRequest(registerController, body);

                // Call sendRequest method using reflection
                HttpResponse<String> response = ReflectionUtils.callSendRequest(registerController, request);

                // Call handleResponse method using reflection
                boolean result = ReflectionUtils.callHandleResponse(registerController, response);

                // Verify that the method returned true
                assertTrue(result);

                // Verify HttpClient interaction
                verify(mockHttpClient).send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class));
        }

        @Test
        public void testRegistrationWithMissingFields() throws Exception {
                String body = setUpMockFields("John Doe", "", "john_doe", "Password123!");

                // Initialize submitError in registerController
                Field submitErrorField = registerController.getClass().getDeclaredField("submitError");
                submitErrorField.setAccessible(true);
                submitErrorField.set(registerController, new Text());

                // Mock the HTTP response
                when(mockResponse.statusCode()).thenReturn(400);
                when(mockResponse.body()).thenReturn("Please fill in all fields");

                // Mock the HttpClient to return the mockResponse
                when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                                .thenReturn(mockResponse);

                HttpRequest request = ReflectionUtils.callCreateHttpRequest(registerController, body);

                HttpResponse<String> response = ReflectionUtils.callSendRequest(registerController, request);

                boolean result = ReflectionUtils.callHandleResponse(registerController, response);

                assertFalse(result);

                verify(mockHttpClient).send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class));
        }

        @Test
        public void testRegistrationWithInvalidCredentials() throws Exception {
                String body = setUpMockFields("John Doe", "12345", "john_doe", "Password123!");

                // Initialize submitError in registerController
                Field submitErrorField = registerController.getClass().getDeclaredField("submitError");
                submitErrorField.setAccessible(true);
                submitErrorField.set(registerController, new Text());

                // Mock the HTTP response
                when(mockResponse.statusCode()).thenReturn(422);
                when(mockResponse.body()).thenReturn("Invalid credentials");

                // Mock the HttpClient to return the mockResponse
                when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                                .thenReturn(mockResponse);

                HttpRequest request = ReflectionUtils.callCreateHttpRequest(registerController, body);

                HttpResponse<String> response = ReflectionUtils.callSendRequest(registerController, request);

                boolean result = ReflectionUtils.callHandleResponse(registerController, response);

                assertFalse(result);

                verify(mockHttpClient).send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class));

        }

        @Test
        public void testRegistrationWithExistingEmailInDatabase() throws Exception {
                // Assume the email "john_doe@example.com" already exists in the database
                String existingEmail = "john_doe@example.com";
                String userName = "john_doe";
                String body = setUpMockFields("john doe", existingEmail, "john_doe", "Password123!");

                // Initialize submitError in registerController
                Field submitErrorField = registerController.getClass().getDeclaredField("submitError");
                submitErrorField.setAccessible(true);
                submitErrorField.set(registerController, new Text());

                // Mock the UserDAO to simulate the email already existing in the database
                IUser existingUser = new IUser("John Doe", existingEmail, userName, "Password123!");
                when(mockUserDAO.findByUsername(userName)).thenReturn(existingUser);

                when(mockResponse.statusCode()).thenReturn(409);
                when(mockResponse.body()).thenReturn("Account already exists");

                when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                                .thenReturn(mockResponse);

                HttpRequest request = ReflectionUtils.callCreateHttpRequest(registerController, body);

                HttpResponse<String> response = ReflectionUtils.callSendRequest(registerController, request);

                boolean result = ReflectionUtils.callHandleResponse(registerController, response);

                assertFalse(result);

                verify(mockHttpClient).send(ArgumentMatchers.any(HttpRequest.class),
                                ArgumentMatchers.any(HttpResponse.BodyHandler.class));
        }
}
