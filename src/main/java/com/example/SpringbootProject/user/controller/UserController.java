package com.example.SpringbootProject.user.controller;

import com.example.SpringbootProject.user.dto.request.UserCreateRequest;
import com.example.SpringbootProject.user.dto.request.UserUpdateRequest;
import com.example.SpringbootProject.user.dto.response.UserCreateResponse;
import com.example.SpringbootProject.user.dto.response.UserDetailResponse;
import com.example.SpringbootProject.user.dto.response.UserSummary;
import com.example.SpringbootProject.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse createUser(
            @Valid @RequestBody UserCreateRequest request
            ){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserDetailResponse getUSer(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PatchMapping("/{id}/roles/{role}")
    public UserDetailResponse assignRole(@PathVariable Long id, @PathVariable Long role){
        return userService.assignRole(id,role);
    }

    @PatchMapping("/{id}/roles/{role}")
    public UserDetailResponse removeRole(@PathVariable Long id, @PathVariable Long role){
        return userService.removeRole(id,role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUSer(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserDetailResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request){
        return userService.updateUser(id, request);
    }

    @GetMapping
    public Page<UserSummary> getUsers(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable){
        return userService.getUsers(pageable);
    }

}

