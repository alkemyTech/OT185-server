package com.alkemy.ong.ports.input.rs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    /*
    * Se debe hacer una petición de tipo POST /auth/login

Deberá validarse el envío de los campos email y password en la petición, y desencriptar la contraseña para autenticarse

Se debe manejar el proceso de Autenticación con SpringSecurity.

Validar email y password en petición

Verificar si existe usuario con el email solicitado

Si existe, comparar contraseña encriptada con contraseña enviada en petición

Devolver al usuario en el caso de que exista y la contraseña sea válida, en el caso de que ocurra un error, devolver {ok: false}*/
    private final PasswordEncoder bcrypt;

    public UserController(BCryptPasswordEncoder bcrypt){
        this.bcrypt = bcrypt;
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> userLogin(@RequestParam(required = true) String emain,
                                       @RequestParam(required = true) String password){



       return ResponseEntity.ok(null);//TODO
    }


}
