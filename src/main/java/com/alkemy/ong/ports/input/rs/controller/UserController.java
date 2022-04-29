package com.alkemy.ong.ports.input.rs.controller;



import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.USER_URI;

@RestController
@RequestMapping(USER_URI)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Valid @NotNull @PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
