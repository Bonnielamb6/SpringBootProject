package com.example.SpringbootProject.role.service;

import com.example.SpringbootProject.exceptions.NoSuchRoleException;
import com.example.SpringbootProject.role.dto.request.RoleCreateRequest;
import com.example.SpringbootProject.role.dto.request.RoleUpdateRequest;
import com.example.SpringbootProject.role.dto.response.RoleResponse;
import com.example.SpringbootProject.role.dto.response.RoleSummary;
import com.example.SpringbootProject.role.mapper.RoleMapper;
import com.example.SpringbootProject.role.model.Role;
import com.example.SpringbootProject.role.repository.IRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(IRoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional
    public RoleResponse createRole(RoleCreateRequest roleToAdd) {
        Role role = new Role(
                roleToAdd.name(),
                roleToAdd.description()
        );
        Role roleCreated = roleRepository.save(role);
        return roleMapper.toResponse(roleCreated);
    }

    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleException(id));
        roleRepository.delete(role);
    }

    public RoleResponse getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleException(id));
        return roleMapper.toResponse(role);
    }

    @Transactional
    public RoleResponse updateRole(Long id, RoleUpdateRequest roleUpdate) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleException(id));
        role.setName(roleUpdate.name());
        role.setDescription(roleUpdate.description());
        return roleMapper.toResponse(role);
    }

    public Page<RoleSummary> getRoles(Pageable pageable) {
        return roleRepository
                .findAll(pageable)
                .map(roleMapper::toSummaryResponse);
    }

}
