package com.example.SpringbootProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestErrorResponse> handleException(Exception ex) {
        RestErrorResponse error = RestErrorResponse.builder().
                status(HttpStatus.BAD_REQUEST.value()).
                message(ex.getMessage()).
                timestamp(Instant.now()).
                build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
