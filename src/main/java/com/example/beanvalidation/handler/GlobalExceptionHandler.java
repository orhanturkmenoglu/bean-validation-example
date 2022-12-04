package com.example.beanvalidation.handler;

import com.example.beanvalidation.exception.ErrorResponse;
import com.example.beanvalidation.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.error("MethodArgumentNotValidExceptionHandler: {}", exception.getMessage());
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getDefaultMessage())
                        .collect(Collectors.joining(", ")))
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(UserNotFoundException exception, HttpServletResponse response) throws IOException {
        log.error("UserNotFoundExceptionHandler: {}", exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception, HttpServletResponse response) throws IOException {
        log.error("ExceptionHandler: {}", exception.getMessage());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
