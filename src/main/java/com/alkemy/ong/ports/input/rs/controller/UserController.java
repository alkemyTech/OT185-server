package com.alkemy.ong.ports.input.rs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@RequestParam(required = true) String email,
                                       @RequestParam(required = true) String password) {


        return ResponseEntity.ok(null);//TODO
    }


}
