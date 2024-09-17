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
import com.example.server.models.IUser;
import com.example.server.models.SessionDAO;
import com.example.server.models.UserDAO;
import com.example.utils.ReflectionUtils;
import com.example.utils.JavaFXInitializer;
import com.example.client.Client;

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
    public void testIsNameValid() {
        assertTrue(IUser.isNameValid("John Doe"), "Valid name should pass");
        assertFalse(IUser.isNameValid("Jo"), "Name too short should fail");
        assertFalse(IUser.isNameValid(""), "Empty name should fail");
        assertFalse(IUser.isNameValid("John123"), "Name with numbers should fail");
    }

    @Test
    public void testIsEmailValid() {
        assertTrue(IUser.isEmailValid("john@example.com"), "Valid email should pass");
        assertFalse(IUser.isEmailValid("john.example.com"), "Email without @ should fail");
        assertFalse(IUser.isEmailValid("john@com"), "Email without domain should fail");
        assertFalse(IUser.isEmailValid("john@.com"), "Email without domain name should fail");
    }

    @Test
    public void testIsUsernameValid() {
        assertTrue(IUser.isUsernameValid("john_doe"), "Valid username should pass");
        assertFalse(IUser.isUsernameValid("jo"), "Username too short should fail");
        assertFalse(IUser.isUsernameValid("john doe"), "Username with spaces should fail");
        assertFalse(IUser.isUsernameValid("john@doe"), "Username with special characters should fail");
    }

    @Test
    public void testIsPasswordValid() {
        assertTrue(IUser.isPasswordValid("Secure123"), "Valid password should pass");
        assertFalse(IUser.isPasswordValid("secure123"), "Password without uppercase should fail");
        assertFalse(IUser.isPasswordValid("SECURE123"), "Password without lowercase should fail");
        assertFalse(IUser.isPasswordValid("Secure"), "Password too short should fail");
    }

    @Test
    public void testSuccessfulRegistration() throws Exception {

    }

    @Test
    public void testRegistrationWithMissingFields() throws Exception {

    }

    @Test
    public void testRegistrationWithInvalidCredentials() throws Exception {

    }

    @Test
    public void testRegistrationWithExistingEmail() throws Exception {

    }

    @Test
    public void testRegistrationServerError() throws Exception {

    }
}
