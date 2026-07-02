package com.example.SpringbootProject.role.dto.request;

public record RoleCreateRequest(
        Long id,
        String name,
        String description
) {
}
