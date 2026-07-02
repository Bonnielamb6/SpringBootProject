package com.example.SpringbootProject.product.dto.response;

public record ProductCreateResponse(
        Long id,
        String name,
        String description
) {
}
