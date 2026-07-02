package com.example.SpringbootProject.user.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email
) {
}
