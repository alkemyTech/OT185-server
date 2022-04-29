package com.alkemy.ong.common.security.services;

import com.alkemy.ong.common.security.utils.JwtUtil;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public String singIn(String email, String password) throws Exception {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return  jwtUtil.generateToken(userDetails);
    }
}
