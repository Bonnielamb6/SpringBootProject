package com.example.SpringbootProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<RestErrorResponse> handleNoSuchUser(NoSuchUserException ex) {
        RestErrorResponse error = RestErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(CategoryNotAssignedException.class)
    public ResponseEntity<RestErrorResponse> handleCategoryNotAssigned(CategoryNotAssignedException ex) {
        RestErrorResponse error = RestErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<RestErrorResponse> handleException(Exception ex) {
        RestErrorResponse error = RestErrorResponse.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Unexpected error occurred.")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
