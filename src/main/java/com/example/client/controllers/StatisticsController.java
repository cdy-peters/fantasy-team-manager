package com.example.client.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import com.example.client.helpers.Statistics;
import com.example.client.helpers.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class StatisticsController implements Initializable {

    @FXML
    private void listUsers() {  
        System.out.println("\nListing users using Java 11 HttpClient:");  
    
        HttpClient httpClient = HttpClient.newHttpClient();  
        HttpRequest request = HttpRequest.newBuilder(URI.create(Utils.USER_API)).GET().build();  
    
        try {  
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());  
    
            int statusCode = response.statusCode();  
            System.out.println("HTTP status: " + statusCode);  
    
            System.out.println("Users returned in request: ");  
            List<Statistics> users = Utils.toList(response.body());  
            users.forEach(System.out::println);  
    
            System.out.println("Headers:");  
            response.headers().map().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));  
        }  
        catch (IOException | InterruptedException e) {  
            throw new RuntimeException(e);  
        }  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code if needed
    }
}