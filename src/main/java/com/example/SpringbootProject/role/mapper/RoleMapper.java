package com.example.SpringbootProject.role.mapper;

import com.example.SpringbootProject.role.dto.response.RoleResponse;
import com.example.SpringbootProject.role.dto.response.RoleSummary;
import com.example.SpringbootProject.role.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toResponse(Role role);

    RoleSummary toSummaryResponse(Role role);
}
