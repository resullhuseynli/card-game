package com.game.card.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {

        return ResponseEntity.badRequest().body(createApiError(ex.getMessage()));

    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(createApiError(ex.getMessage()));

    }


    public <T> ApiResponse<T> createApiError(T error) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setId(UUID.randomUUID().toString());
        apiResponse.setErrorTime(new Date());
        apiResponse.setError(error);
        return apiResponse;
    }


}
