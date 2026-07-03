package com.example.SpringbootProject.user.dto.response;

public record UserCreateResponse(
        Long id,
        String name,
        String email
) {
}
