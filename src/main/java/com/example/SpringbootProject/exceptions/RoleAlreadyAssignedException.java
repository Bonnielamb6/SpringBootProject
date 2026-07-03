package com.example.SpringbootProject.exceptions;

public class RoleAlreadyAssignedException extends RuntimeException {
    public RoleAlreadyAssignedException(Long userId, Long roleId) {
        super("Role with id " + roleId + " is already assigned to user with id " + userId + ".");
    }
}
