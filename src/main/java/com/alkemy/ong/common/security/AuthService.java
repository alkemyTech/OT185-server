package com.alkemy.ong.common.security;

import com.alkemy.ong.common.exception.BadUserLoginException;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationRequest authRequest;
    private final AuthenticationResponse authResponse;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (Exception e) {
            throw new BadUserLoginException("There is a problem at login");
        }
        //TODO
        // final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse("jwt");

    }
}

