package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Hyperlink createAccountLink;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onCreateAccountLinkClick() throws IOException {
        Stage stage = (Stage) createAccountLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), App.WIDTH, App.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSubmitButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username);
        System.out.println(password);
    }
}
