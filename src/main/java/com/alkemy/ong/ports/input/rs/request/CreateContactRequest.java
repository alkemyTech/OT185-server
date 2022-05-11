package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {


    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("phone")
    private String phone;

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("message")
    private String message;



}
