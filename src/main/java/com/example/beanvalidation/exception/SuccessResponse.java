package com.example.beanvalidation.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessResponse {

    public static <T> ResponseEntity<T> responseBuilder(String message, HttpStatus httpStatus, T data) {
        return getResponseEntity(message, httpStatus, data);
    }

    public static <T> ResponseEntity<T> responseBuilder(String message, HttpStatus httpStatus) {
        return getResponseEntity(message, httpStatus, null);
    }

    public static <T> ResponseEntity<List<T>> responseBuilder(String message, HttpStatus httpStatus, List<T> data) {
        return getResponseEntity(message, httpStatus, data);
    }


    @NotNull
    private static <T> ResponseEntity<T> getResponseEntity(String message, HttpStatus httpStatus, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", message);
        response.put("statusCode", httpStatus.value());
        response.put("statusInfo", httpStatus.getReasonPhrase());
        response.put("data", data);

        return new ResponseEntity(response, httpStatus);
    }

}
