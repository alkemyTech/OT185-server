package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.common.security.services.AuthenticationService;
import com.alkemy.ong.common.security.services.UserDetailsCustomService;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    private final UserDetailsCustomService userDetailsCustomService;

    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(this.authService.login(authRequest));
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse meData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return userDetailsCustomService.meData(authentication.getName());
    }
}