package com.example.client.helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.example.client.Client;

import java.io.IOException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Helper class for creating and sending HTTP requests.
 */
public class HttpHelper {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    private static final String SERVER_URL = dotenv == null ? System.getenv("SERVER_URL") : dotenv.get("SERVER_URL");

    private HttpClient httpClient;
    private HttpRequest request;

    /**
     * Create a new HttpHelper instance.
     */
    public HttpHelper() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Create a new HttpHelper instance with a custom HttpClient.
     * 
     * @param httpClient The HttpClient
     */
    public HttpHelper(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Create a new HttpHelper instance with a GET request of the given path.
     * 
     * @param requestUrl The request path
     */
    public HttpHelper(String requestUrl) {
        this.httpClient = HttpClient.newHttpClient();
        this.request = request(requestUrl);
    }

    /**
     * Create a new HttpHelper instance with a POST request of the given path and
     * body.
     * 
     * @param requestUrl The request path
     * @param body       The request body
     */
    public HttpHelper(String requestUrl, String body) {
        this.httpClient = HttpClient.newHttpClient();
        this.request = request(requestUrl, body);
    }

    /**
     * Create a new GET request with the given path.
     * 
     * @param path The request path
     * @return The HttpRequest
     */
    public HttpRequest request(String path) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL + path))
                .header("Content-Type", "application/json")
                .header("Authorization", Client.sessionToken)
                .build();
        return this.request;
    }

    /**
     * Create a new POST request with the given path and body.
     * 
     * @param path The request path
     * @param body The request body
     * @return The HttpRequest
     */
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

    /**
     * Send the request.
     * 
     * @return The HttpResponse as a String
     * @throws IOException          If an error occurs while sending the request
     * @throws InterruptedException If the request is interrupted
     */
    public HttpResponse<String> send() throws IOException, InterruptedException {
        return httpClient.send(request, BodyHandlers.ofString());
    }

    /**
     * Send the request with a custom BodyHandler.
     * 
     * @param bodyHandler The BodyHandler
     * @param <T>         The type of the response body
     * @return The HttpResponse as the specified type
     * @throws IOException          If an error occurs while sending the request
     * @throws InterruptedException If the request is interrupted
     */
    public <T> HttpResponse<T> send(HttpResponse.BodyHandler<T> bodyHandler)
            throws IOException, InterruptedException {
        return httpClient.send(request, bodyHandler);
    }
}
