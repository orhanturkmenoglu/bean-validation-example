package com.example.beanvalidation.handler;

import com.example.beanvalidation.exception.ErrorException;
import com.example.beanvalidation.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error-> {
            ErrorException builder = builder(error.getDefaultMessage());
            errorMap.put("Stamp", builder.getStamp());
            errorMap.put(error.getField(), builder.getMessage());
            errorMap.put("status", builder.getStatus());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorException handleUserNotFoundException(UserNotFoundException exception) {
        return builder(exception.getMessage());
    }

    private ErrorException builder(String exceptionMessage) {
        return ErrorException.builder()
                .stamp(new Date())
                .message(exceptionMessage)
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .build();
    }
}
