package com.example.SpringbootProject.user.dto.response;

import com.example.SpringbootProject.role.dto.response.RoleSummary;

import java.util.Set;

public record UserDetailResponse(
        Long id,
        String username,
        String email,
        Set<RoleSummary> roles
) {
}
