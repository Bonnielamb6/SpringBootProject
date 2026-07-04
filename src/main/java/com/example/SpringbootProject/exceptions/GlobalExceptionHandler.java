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

    @ExceptionHandler(CategoryAlreadyAssignedException.class)
    public ResponseEntity<RestErrorResponse> handleCategoryAlreadyAssigned(CategoryAlreadyAssignedException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<RestErrorResponse> handleInsufficientStock(InsufficientStockException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<RestErrorResponse> handleInvalidOrderStatus(InvalidOrderStatusException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<RestErrorResponse> handleNoSuchCategory(NoSuchCategoryException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ResponseEntity<RestErrorResponse> handleNoSuchOrder(NoSuchOrderException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<RestErrorResponse> handleNoSuchProduct(NoSuchProductException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoSuchRoleException.class)
    public ResponseEntity<RestErrorResponse> handleNoSuchRole(NoSuchRoleException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RoleAlreadyAssignedException.class)
    public ResponseEntity<RestErrorResponse> handleRoleAlreadyAssigned(RoleAlreadyAssignedException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RoleNotAssignedException.class)
    public ResponseEntity<RestErrorResponse> handleRoleNotAssigned(RoleNotAssignedException ex) {
        RestErrorResponse errorResponse = RestErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
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
