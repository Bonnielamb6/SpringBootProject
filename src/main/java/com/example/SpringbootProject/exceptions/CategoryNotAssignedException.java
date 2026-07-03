package com.example.SpringbootProject.exceptions;

public class CategoryNotAssignedException extends RuntimeException {
    public CategoryNotAssignedException(Long productId, Long categoryId) {
        super("Product with id " + productId + " does not have category  " + categoryId + " assigned.");
    }
}
