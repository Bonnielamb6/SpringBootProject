package com.example.SpringbootProject.exceptions;

public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException(Long id){
        super("Order with id " + id +" was not found.");
    }
}
