package com.example.SpringbootProject.user.service;

import com.example.SpringbootProject.exceptions.NoSuchRoleException;
import com.example.SpringbootProject.exceptions.NoSuchUserException;
import com.example.SpringbootProject.role.model.Role;
import com.example.SpringbootProject.role.repository.IRoleRepository;
import com.example.SpringbootProject.user.dto.request.UserCreateRequest;
import com.example.SpringbootProject.user.dto.response.UserResponse;
import com.example.SpringbootProject.user.model.User;
import com.example.SpringbootProject.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    //TODO: ADD PASSWORD ENCODER FROM SPRING SECURITY
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse saveUser(UserCreateRequest dtoUserToCreate) {
        User user = new User(
                dtoUserToCreate.name(),
                dtoUserToCreate.email(),
                dtoUserToCreate.password(),
                null,
                null
        );
        User userCreated = userRepository.save(user);
        return new UserResponse(
                userCreated.getId(),
                userCreated.getName(),
                userCreated.getEmail()
        );
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NoSuchUserException(id);
        }
    }

    public UserResponse assignRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUserException(userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NoSuchRoleException(roleId));
        user.addRole(role);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    //TODO: left to see if it is needed different methods for updating mail and/or password
    public UserResponse updateUser(Long id, UserCreateRequest userUpdate) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        user.setName(userUpdate.name());
        return new UserResponse(
                user.getId(),
                userUpdate.name(),
                user.getEmail()
        );
    }

}
