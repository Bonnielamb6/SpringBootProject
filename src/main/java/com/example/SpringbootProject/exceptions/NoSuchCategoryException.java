package com.example.SpringbootProject.exceptions;

public class NoSuchCategoryException extends RuntimeException {
    public NoSuchCategoryException(Long id) {
        super("No such category with id:" + id);
    }
}
