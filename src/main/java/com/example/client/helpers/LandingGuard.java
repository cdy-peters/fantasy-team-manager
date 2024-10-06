package com.example.client.helpers;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import com.google.gson.Gson;
import com.example.client.Client;
import com.example.client.helpers.LandingGuard;
import com.example.server.models.IUserRoster;

public class LandingGuard {
    private final HttpClient httpClient;
    private final Gson gson;

    public LandingGuard() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    private HttpRequest createHttpRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(Client.SERVER_URL + "roster"))
                .header("Content-Type", "application/json")
                .header("Cookie", Client.sessionCookie)
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Optional<IUserRoster> fetchRoster() {
        if (Client.userRoster != null) {
            return Optional.of(Client.userRoster);
        }

        try {
            HttpRequest request = createHttpRequest();
            HttpResponse<String> response = sendRequest(request);

            if (response.statusCode() == 200) {
                return Optional.ofNullable(gson.fromJson(response.body(), IUserRoster.class));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public String getView() {
        if (Client.sessionCookie.isEmpty()) {
            return "/login-view.fxml";
        }

        Optional<IUserRoster> roster = fetchRoster();
        Client.userRoster = roster.orElse(null);

        return Client.userRoster == null ? "/create-roster-view.fxml" : "/home-view.fxml";
    }
}
