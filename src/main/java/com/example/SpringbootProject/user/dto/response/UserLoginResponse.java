package com.example.SpringbootProject.user.dto.response;

public record UserLoginResponse(
        Long id,
        String email,
        String name,
        String token
) {
}
