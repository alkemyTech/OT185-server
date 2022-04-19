package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl {

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (Exception e) {
            //TODO -> crear BadUserLoginException
            throw new NotFoundException("There is a problem at login");
        }
        //TODO
        // final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse("jwt");

    }
}
