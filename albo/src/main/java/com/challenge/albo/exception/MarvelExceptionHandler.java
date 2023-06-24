package com.challenge.albo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MarvelExceptionHandler {
    @ExceptionHandler(MarvelException.class)
    protected ResponseEntity<MarvelError> handleApiException(MarvelException e) {
        Integer statusCode = e.getStatus();
        MarvelError apiError = new MarvelError(e.getMessage());
        return ResponseEntity.status(statusCode).body(apiError);
    }
}
