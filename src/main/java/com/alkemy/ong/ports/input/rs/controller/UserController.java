package com.alkemy.ong.ports.input.rs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder bcrypt;

    public UserController(BCryptPasswordEncoder bcrypt) {
        this.bcrypt = bcrypt;
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@RequestParam(required = true) String emain,
                                       @RequestParam(required = true) String password) {


        return ResponseEntity.ok(null);//TODO
    }


}
