package com.alkemy.ong.ports.input.rs.controller;

<<<<<<< HEAD

import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

=======
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RequestMapping("auth")
>>>>>>> main
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

<<<<<<< HEAD
    private final AuthenticationService authService;

    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(this.authService.login(authRequest));
    }
}
=======
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userService;
    //private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        UserDetails user = userService.loadUserByUsername(authRequest.getEmail());

        //final String jwt = jwtUtils.getToken(user);
        return ResponseEntity.ok(new AuthenticationResponse("jwt"));
    }
}
>>>>>>> main
