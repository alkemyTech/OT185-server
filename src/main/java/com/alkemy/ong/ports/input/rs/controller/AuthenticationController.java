package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.security.services.AuthenticationService;
import com.alkemy.ong.common.security.services.UserDetailsCustomService;
import com.alkemy.ong.common.security.utils.JwtUtil;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    private final JwtUtil jwtUtil;

    private final UserDetailsCustomService userDetailsCustomService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {


            String jwt = authService.singIn(authRequest);


            return ResponseEntity.ok(new AuthenticationResponse(jwt));


    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)


    public UserResponse meData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {


        return userDetailsCustomService.meData(authentication.getName());
    }

}


