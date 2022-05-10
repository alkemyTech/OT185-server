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

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty
    private String phone;

    @NotBlank
    @JsonProperty
    private String email;

    @NotBlank
    @JsonProperty
    private String message;



}
