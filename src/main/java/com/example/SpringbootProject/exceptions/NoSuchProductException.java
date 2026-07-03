package com.example.SpringbootProject.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(Long id) {
        super("Product with id " + id + " was not found.");
    }
}
