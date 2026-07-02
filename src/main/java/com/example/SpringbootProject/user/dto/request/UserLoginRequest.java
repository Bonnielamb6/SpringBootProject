package com.example.SpringbootProject.user.dto.request;

public record UserLoginRequest(
        String email,
        String password
) {
}
