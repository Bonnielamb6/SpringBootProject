package com.example.SpringbootProject.user.service;

import com.example.SpringbootProject.exceptions.NoSuchRoleException;
import com.example.SpringbootProject.exceptions.NoSuchUserException;
import com.example.SpringbootProject.exceptions.RoleAlreadyAssignedException;
import com.example.SpringbootProject.exceptions.RoleNotAssignedException;
import com.example.SpringbootProject.role.model.Role;
import com.example.SpringbootProject.role.repository.IRoleRepository;
import com.example.SpringbootProject.user.dto.request.UserCreateRequest;
import com.example.SpringbootProject.user.dto.request.UserUpdateRequest;
import com.example.SpringbootProject.user.dto.response.UserCreateResponse;
import com.example.SpringbootProject.user.dto.response.UserDetailResponse;
import com.example.SpringbootProject.user.dto.response.UserSummary;
import com.example.SpringbootProject.user.mapper.UserMapper;
import com.example.SpringbootProject.user.model.User;
import com.example.SpringbootProject.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;

    //TODO: ADD PASSWORD ENCODER FROM SPRING SECURITY
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = new User(
                request.name(),
                request.email(),
                request.password()
        );
        User userCreated = userRepository.save(user);
        return userMapper.toCreateResponse(userCreated);
    }

    public UserDetailResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        return userMapper.toDetailResponse(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        userRepository.delete(user);
    }

    @Transactional
    public UserDetailResponse assignRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUserException(userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NoSuchRoleException(roleId));
        if (user.getRoles().contains(role)) {
            throw new RoleAlreadyAssignedException(userId, roleId);
        }
        user.addRole(role);
        return userMapper.toDetailResponse(user);
    }

    @Transactional
    public UserDetailResponse removeRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUserException(userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NoSuchRoleException(roleId));
        user.removeRole(role);
        if (!user.getRoles().contains(role)) {
            throw new RoleNotAssignedException(userId, roleId);
        }
        return userMapper.toDetailResponse(user);
    }

    //TODO: left to see if it is needed different methods for updating mail and/or password
    @Transactional
    public UserDetailResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        user.setName(request.name());
        user.setEmail(request.email());
        return userMapper.toDetailResponse(user);
    }

    public Page<UserSummary> getUsers(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::toSummary);
    }

}
