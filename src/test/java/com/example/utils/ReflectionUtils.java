package com.example.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReflectionUtils {

    public static boolean callHandleResponse(Object instance, HttpResponse<String> response) throws Exception {
        Class<?> clazz = instance.getClass();

        // Get the handleResponse method
        Method method = clazz.getDeclaredMethod("handleResponse", HttpResponse.class);

        method.setAccessible(true);

        return (boolean) method.invoke(instance, response);
    }

    public static HttpRequest callCreateHttpRequest(Object instance, String body) throws Exception {
        Class<?> clazz = instance.getClass();

        // Get the createHttpRequest method
        Method method = clazz.getDeclaredMethod("createHttpRequest", String.class);

        method.setAccessible(true);

        // Invoke the method and return the result
        return (HttpRequest) method.invoke(instance, body);
    }

    public static HttpResponse<String> callSendRequest(Object instance, HttpRequest request) throws Exception {
        Class<?> clazz = instance.getClass();

        // Get the sendRequest method
        Method method = clazz.getDeclaredMethod("sendRequest", HttpRequest.class);

        method.setAccessible(true);

        // Invoke the method and return the result
        return (HttpResponse<String>) method.invoke(instance, request);
    }


    public static Object invokePrivateMethod(Object obj, String methodName, Class<?>[] paramTypes, Object... params)
            throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(obj, params);
    }

    public static void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
