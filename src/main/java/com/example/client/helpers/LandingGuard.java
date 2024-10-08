package com.example.client.helpers;

import java.io.IOException;

import java.net.http.HttpResponse;
import java.util.Optional;

import com.google.gson.Gson;
import com.example.client.Client;
import com.example.client.helpers.LandingGuard;
import com.example.server.models.IUserRoster;

public class LandingGuard {
    private final Gson gson;

    public LandingGuard() {
        this.gson = new Gson();
    }

    private Optional<IUserRoster> fetchRoster() {
        if (Client.userRoster != null) {
            return Optional.of(Client.userRoster);
        }

        HttpHelper request = new HttpHelper("roster");
        try {
            HttpResponse<String> response = request.send();

            if (response.statusCode() == 200) {
                return Optional.ofNullable(gson.fromJson(response.body(), IUserRoster.class));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public String getView() {
        if (Client.sessionToken.isEmpty()) {
            return "/login-view.fxml";
        }

        Optional<IUserRoster> roster = fetchRoster();
        Client.userRoster = roster.orElse(null);

        return Client.userRoster == null ? "/create-roster-view.fxml" : "/home-view.fxml";
    }
}
