package com.example.SpringbootProject.user.dto.request;

public record UserCreateRequest(
        String name,
        String email,
        String password
) {
}
