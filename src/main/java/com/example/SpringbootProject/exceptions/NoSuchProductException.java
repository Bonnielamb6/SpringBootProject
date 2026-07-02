package com.example.SpringbootProject.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(Long id) {
        super("No such product with id:" + id);
    }
}
