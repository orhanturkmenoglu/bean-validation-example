
package com.example.beanvalidation.handler;

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
public class ApplicationExceptionHandler {
    public ApplicationExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errorMap.put("Stamp", new Date());
            errorMap.put(error.getField(), error.getDefaultMessage());
            errorMap.put("status", HttpStatus.BAD_REQUEST);
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler({UserNotFoundException.class})
    public Map<String, String> handleBusinessException(UserNotFoundException exception) {
        Map<String, String> errorMap = new HashMap();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
