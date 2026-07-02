package com.example.SpringbootProject.role.service;

import com.example.SpringbootProject.exceptions.NoSuchRoleException;
import com.example.SpringbootProject.role.dto.request.RoleCreateRequest;
import com.example.SpringbootProject.role.dto.response.RoleResponse;
import com.example.SpringbootProject.role.model.Role;
import com.example.SpringbootProject.role.repository.IRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse saveRole(RoleCreateRequest roleToAdd) {
        Role role = new Role(
                roleToAdd.name(),
                roleToAdd.description(),
                null
        );

        Role roleCreated = roleRepository.save(role);
        return new RoleResponse(
                roleCreated.getId(),
                roleCreated.getName(),
                roleCreated.getDescription()
        );
    }

    public void deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new NoSuchRoleException(id);
        }
    }

    public RoleResponse getRole(Long id) {
        Role roleToReturn = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleException(id));
        return new RoleResponse(
                roleToReturn.getId(),
                roleToReturn.getName(),
                roleToReturn.getDescription()
        );
    }

    public RoleResponse updateRole(Long id, RoleCreateRequest roleUpdate) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleException(id));
        role.setName(roleUpdate.name());
        role.setDescription(roleUpdate.description());
        roleRepository.save(role);
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }

}
