package com.example.SpringbootProject.exceptions;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException(Long id) {
        super("Role with id " + id + " was not found.");
    }
}
