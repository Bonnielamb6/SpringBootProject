package com.example.SpringbootProject.exceptions;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(Long id) {
        super("User with id " + id + " was not found.");
    }
}
