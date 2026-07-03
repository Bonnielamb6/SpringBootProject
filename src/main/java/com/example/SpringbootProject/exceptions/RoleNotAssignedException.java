package com.example.SpringbootProject.exceptions;

public class RoleNotAssignedException extends RuntimeException {
    public RoleNotAssignedException(Long userId, Long roleId) {
        super("Role with id " + roleId + " is not assigned to user with id " + userId + ".");
    }
}
