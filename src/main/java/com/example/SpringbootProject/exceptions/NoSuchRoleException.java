package com.example.SpringbootProject.exceptions;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException(Long id) {
        super("No such role with id: " + id);
    }
}
