package com.example.SpringbootProject.exceptions;

public class NoSuchCategoryException extends RuntimeException {
    public NoSuchCategoryException(Long id) {
        super("Category with id " + id + " was not found.");
    }
}
