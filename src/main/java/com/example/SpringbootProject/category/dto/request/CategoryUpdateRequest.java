package com.example.SpringbootProject.category.dto.request;

public record CategoryUpdateRequest(
        Long id,
        String name,
        String description
) {
}
