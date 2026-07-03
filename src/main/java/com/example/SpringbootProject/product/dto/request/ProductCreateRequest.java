package com.example.SpringbootProject.product.dto.request;

public record ProductCreateRequest(
        String name,
        String description,
        Integer stock
) {
}
