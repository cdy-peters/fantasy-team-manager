package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.example.client.controllers.LoginController;
import com.example.client.controllers.RegisterController;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;
import com.example.utils.JavaFXInitializer;
import com.example.utils.ReflectionUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginTests {
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
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        JavaFXInitializer.initialize(); // Initialize JavaFX toolkit
        MockitoAnnotations.openMocks(this);
        // Set up mocks for the JavaFX components if needed
        // Example: when(mockStage.getScene()).thenReturn(new Scene(new Group(), 800,
        // 600));
    }

    private String setUpMockFields(String username, String password) throws Exception {
        // Use reflection to access and set private fields
        setPrivateField("usernameField", new TextField(username));
        PasswordField passwordField = new PasswordField();
        passwordField.setText(password);
        setPrivateField("passwordField", passwordField);

        // Create HTTP body
        return String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                username, password);
    }

    private void invokePrivateMethod(String methodName) throws Exception {
        ReflectionUtils.invokePrivateMethod(loginController, methodName, new Class<?>[] {});
    }

    private Object getPrivateField(String fieldName) throws Exception {
        return ReflectionUtils.getPrivateField(loginController, fieldName);
    }

    private void setPrivateField(String fieldName, Object value) throws Exception {
        Field field = RegisterController.class.getDeclaredField(fieldName);
        field.setAccessible(true);

    }

    @Test
    public void testSuccessfulLogin() throws Exception {

        // Mock the HTTP response
        when(mockResponse.statusCode()).thenReturn(201);
        Map<String, List<String>> headersMap = Map.of("Authorization", List.of("session=1234"));
        when(mockResponse.headers()).thenReturn(HttpHeaders.of(headersMap, (k, v) -> true));

        // Mock the HttpClient to return the mockResponse
        when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Call the createHttpRequest method using reflection
        String body = setUpMockFields("john_doe", "password");
        HttpRequest request = ReflectionUtils.callCreateHttpRequest(loginController, body);

        // Send the request
        HttpResponse<String> response = mockHttpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response
        boolean success = ReflectionUtils.callHandleResponse(loginController, response);

        // Assert the login was successful
        assertTrue(success, "Login should be successful");
    }

    @Test
    public void testLoginWithMissingFields() throws Exception {

        // Initialize submitError in loginController
        Field submitErrorField = loginController.getClass().getDeclaredField("submitError");
        submitErrorField.setAccessible(true);
        submitErrorField.set(loginController, new Text());

        // Mock the HTTP response
        when(mockResponse.statusCode()).thenReturn(400);
        Map<String, List<String>> headersMap = Map.of("Authorization", List.of("session=1234"));
        when(mockResponse.headers()).thenReturn(HttpHeaders.of(headersMap, (k, v) -> true));

        // Mock the HttpClient to return the mockResponse
        when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Call the createHttpRequest method using reflection
        String body = setUpMockFields("", "");
        HttpRequest request = ReflectionUtils.callCreateHttpRequest(loginController, body);

        // Send the request
        HttpResponse<String> response = mockHttpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response
        boolean success = ReflectionUtils.callHandleResponse(loginController, response);

        // Assert the login was unsuccessful
        assertFalse(success);
    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {

        // Initialize submitError in loginController
        Field submitErrorField = loginController.getClass().getDeclaredField("submitError");
        submitErrorField.setAccessible(true);
        submitErrorField.set(loginController, new Text());

        // Mock the HTTP response
        when(mockResponse.statusCode()).thenReturn(401);
        Map<String, List<String>> headersMap = Map.of("Authorization", List.of("session=1234"));
        when(mockResponse.headers()).thenReturn(HttpHeaders.of(headersMap, (k, v) -> true));

        // Mock the HttpClient to return the mockResponse
        when(mockHttpClient.send(ArgumentMatchers.any(HttpRequest.class),
                ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Call the createHttpRequest method using reflection
        String body = setUpMockFields("", "");
        HttpRequest request = ReflectionUtils.callCreateHttpRequest(loginController, body);

        // Send the request
        HttpResponse<String> response = mockHttpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response
        boolean success = ReflectionUtils.callHandleResponse(loginController, response);

        // Assert the login was unsuccessful
        assertFalse(success);
    }

}
