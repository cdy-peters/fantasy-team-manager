package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.client.controllers.RegisterController;
import com.example.client.helpers.PrefsHelper;
import com.example.utils.ReflectionUtils;
import com.example.utils.JavaFXInitializer;
import com.example.client.Client;

public class RegisterTests {
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
    public void testSuccessfulRegistration() throws Exception {
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
        String body = setUpMockFields("John Doe", "john@example.com", "john_doe", "securePass");

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
}
