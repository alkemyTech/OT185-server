package com.alkemy.ong.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class AuthenticationRequest {

    @Email(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
    @NotBlank(message ="The email must not be empty")
    private String email;


    @Size(min = 4, max = 20, message = "The password must not be empty" )
    private String password;
}
