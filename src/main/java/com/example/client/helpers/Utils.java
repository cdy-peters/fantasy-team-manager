package com.example.client.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {  
  
    private Utils() {}  
  
    public static final String USER_API = "http://localhost:8080/player_statistics";  
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();  
  
    public static List<Statistics> toList(InputStream inputStream) {  
        try {  
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {});  
        }  
        catch (IOException exc) {  
            throw new UncheckedIOException(exc);  
        }  
    }  
  
    public static Statistics toObject(InputStream inputStream) {  
        try {  
            return OBJECT_MAPPER.readValue(inputStream, Statistics.class);  
        }  
        catch (IOException exc) {  
            throw new UncheckedIOException(exc);  
        }  
    }  
  
    public static String toJson(Statistics statistics) {  
        try {  
            return OBJECT_MAPPER.writeValueAsString(statistics);  
        }  
        catch (JsonProcessingException exc) {  
            throw new UncheckedIOException(exc);  
        }
    }
}
