package com.example.SpringbootProject.exceptions;

public class CategoryAlreadyAssignedException extends RuntimeException {
    public CategoryAlreadyAssignedException(Long productId, Long categoryId) {
        super("Product with id: " + productId + " already has category with id: " + categoryId + " assigned");
    }
}
