package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.usecase.AuthenticationService;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(this.authService.login(authRequest));
    }
}
