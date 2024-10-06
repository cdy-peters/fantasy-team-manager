package com.example.client;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import com.example.client.helpers.PrefsHelper;
import com.example.server.models.IUserRoster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import io.github.cdimascio.dotenv.Dotenv;

public class Client extends Application {
    private HttpClient httpClient = HttpClient.newHttpClient();
    private Gson g = new Gson();

    public static Dotenv dotenv = Dotenv.load();
    public static final String SERVER_URL = dotenv.get("SERVER_URL");

    public static final String TITLE = "Fantasy Team Manager";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    // TODO: Handle expired session cookie
    public static String sessionCookie = PrefsHelper.getPref("sessionCookie");

    public static IUserRoster userRoster = null;

    private HttpRequest createHttpRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "roster"))
                .header("Content-Type", "application/json")
                .header("Cookie", sessionCookie)
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void fetchRoster() {
        try {
            HttpRequest request = createHttpRequest();
            HttpResponse<String> response = sendRequest(request);
            if (response.statusCode() != 200) {
                return;
            }

            userRoster = g.fromJson(response.body(), IUserRoster.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        String view;
        if (sessionCookie.isEmpty()) {
            view = "/login-view.fxml";
        } else {
            fetchRoster();
            view = userRoster == null ? "/create-roster-view.fxml" : "/home-view.fxml";
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
