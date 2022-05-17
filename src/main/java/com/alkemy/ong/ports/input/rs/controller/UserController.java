package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.UserControllerMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;


import com.alkemy.ong.domain.usecase.UserService;

import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.USER_URI;

@RestController
@RequestMapping(USER_URI)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    private final UserControllerMapper userControllerMapper;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {

        User user = userControllerMapper.updateUserRequestToUser(updateUserRequest);

        userService.updateEntityIfExists(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Valid @NotNull @PathVariable Long id) {
        userService.deleteUserById(id);

    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(){
        List<User> users = userService.getAll();
        List<UserResponse> responses = userControllerMapper.userListToUserResponse(users);

        return ResponseEntity.ok().body(responses);
    }

}
