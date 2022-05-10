package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {


    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty
    private String phone;

    @NotBlank
    @JsonProperty
    private String email;

    @JsonProperty
    private String message;



}
