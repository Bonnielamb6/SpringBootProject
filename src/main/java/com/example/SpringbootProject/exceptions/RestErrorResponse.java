package com.example.SpringbootProject.exceptions;

import lombok.Builder;

import java.time.Instant;

@Builder
public record RestErrorResponse(
        int status,
        String message,
        Instant timestamp
) {
}
