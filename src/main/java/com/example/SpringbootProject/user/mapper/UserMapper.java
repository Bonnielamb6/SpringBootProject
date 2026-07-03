package com.example.SpringbootProject.user.mapper;

import com.example.SpringbootProject.user.dto.response.UserSummary;
import com.example.SpringbootProject.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserSummary toSummary(User user);
}
