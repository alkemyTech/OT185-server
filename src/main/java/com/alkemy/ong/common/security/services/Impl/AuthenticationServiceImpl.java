package com.alkemy.ong.common.security.services.Impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.common.security.services.AuthenticationService;
import com.alkemy.ong.common.security.utils.JwtUtil;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (Exception e) {

            throw new NotFoundException("There is a problem at login");
        }

         final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);

    }
}
