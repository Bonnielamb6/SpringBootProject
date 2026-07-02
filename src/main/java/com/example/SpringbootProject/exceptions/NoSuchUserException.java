package com.example.SpringbootProject.exceptions;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(Long id) {
        super("No such User with id" + id);
    }
}
