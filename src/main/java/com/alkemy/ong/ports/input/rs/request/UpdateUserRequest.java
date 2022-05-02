package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {


    @JsonProperty("first_name")
    private String firstName;


    @JsonProperty("last_name")
    private String lastName;


    @JsonProperty("password")
    private String password;

    private String photo;
}
