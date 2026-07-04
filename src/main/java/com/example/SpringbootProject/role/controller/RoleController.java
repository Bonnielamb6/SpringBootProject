package com.example.SpringbootProject.role.controller;

import com.example.SpringbootProject.role.dto.request.RoleCreateRequest;
import com.example.SpringbootProject.role.dto.request.RoleUpdateRequest;
import com.example.SpringbootProject.role.dto.response.RoleResponse;
import com.example.SpringbootProject.role.dto.response.RoleSummary;
import com.example.SpringbootProject.role.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(
            @Valid @RequestBody RoleCreateRequest request) {
        return roleService.createRole(request);
    }

    @GetMapping("/{id}")
    public RoleResponse getRole(
            @PathVariable Long id) {
        return roleService.getRole(id);
    }

    @GetMapping
    public Page<RoleSummary> getRoles(
            Pageable pageable) {
        return roleService.getRoles(pageable);
    }

    @PutMapping("/{id}")
    public RoleResponse updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequest request) {
        return roleService.updateRole(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(
            @PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
