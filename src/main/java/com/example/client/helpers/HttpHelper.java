package com.example.client.helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.example.client.Client;

import java.io.IOException;

import io.github.cdimascio.dotenv.Dotenv;

public class HttpHelper {
    private static Dotenv dotenv = Dotenv.load();
    private static final String SERVER_URL = dotenv.get("SERVER_URL");

    private HttpClient httpClient;
    private HttpRequest request;

    public HttpHelper() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public HttpHelper(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpHelper(String requestUrl) {
        this.httpClient = HttpClient.newHttpClient();
        this.request = request(requestUrl);
    }

    public HttpHelper(String requestUrl, String body) {
        this.httpClient = HttpClient.newHttpClient();
        this.request = request(requestUrl, body);
    }

    public HttpRequest request(String path) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL + path))
                .header("Content-Type", "application/json")
                .header("Authorization", Client.sessionToken)
                .build();
        return this.request;
    }

    public HttpRequest request(String path, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL + path))
                .header("Content-Type", "application/json")
                .header("Authorization", Client.sessionToken);

        if (body != "") {
            builder.POST(HttpRequest.BodyPublishers.ofString(body));
        } else {
            builder.POST(HttpRequest.BodyPublishers.noBody());
        }

        this.request = builder.build();
        return this.request;
    }

    public HttpResponse<String> send() throws IOException, InterruptedException {
        return httpClient.send(request, BodyHandlers.ofString());
    }

    public <T> HttpResponse<T> send(HttpResponse.BodyHandler<T> bodyHandler)
            throws IOException, InterruptedException {
        return httpClient.send(request, bodyHandler);
    }
}
