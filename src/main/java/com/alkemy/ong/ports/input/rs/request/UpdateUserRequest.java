package com.alkemy.ong.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String photo;
}
