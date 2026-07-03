package com.example.SpringbootProject.user.mapper;

import com.example.SpringbootProject.role.mapper.RoleMapper;
import com.example.SpringbootProject.user.dto.response.UserCreateResponse;
import com.example.SpringbootProject.user.dto.response.UserDetailResponse;
import com.example.SpringbootProject.user.dto.response.UserSummary;
import com.example.SpringbootProject.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserSummary toSummary(User user);

    UserCreateResponse toCreateResponse(User user);

    UserDetailResponse toDetailResponse(User user);
}
