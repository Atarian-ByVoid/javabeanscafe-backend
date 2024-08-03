package com.javabeanscafe.application.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingFields handleConstraintViolationExcpetion(ConstraintViolationException exception,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        exception.getConstraintViolations().forEach(error -> errors.put(
                Objects.requireNonNull(StreamSupport.stream(error.getPropertyPath().spliterator(), false)
                        .reduce((first, second) -> second).orElse(null)).toString(),
                error.getMessage()));

        return new ErrorHandlingFields(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getServletPath(), request.getMethod(), errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandling handleServletRequestParameterException(MissingServletRequestParameterException exception,
            HttpServletRequest request) {
        return new ErrorHandling(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), request.getServletPath(), request.getMethod(),
                exception.getParameterName().concat(" é " +
                        "obrigatório na requisição"));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorHandling> handleUserException(CustomException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(
                        new ErrorHandling(LocalDateTime.now(), exception.getStatusCode().value(),
                                request.getServletPath(), request.getMethod(),
                                exception.getMessage()));
    }

    public record ErrorHandlingFields(LocalDateTime timestamps, Integer statusCode, String message, String path,
            String method,
            Map<String, String> errors) {
    }

    public record ErrorHandling(LocalDateTime timestamps, Integer statusCode, String path, String method,
            String message) {
    }
}
