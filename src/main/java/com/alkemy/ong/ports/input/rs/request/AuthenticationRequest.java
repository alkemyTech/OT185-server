package com.alkemy.ong.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    @Email(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
    @NotBlank(message ="The email must not be empty")
    private String email;


    @Size(min = 4, max = 20, message = "The password must not be empty" )
    private String password;
}
