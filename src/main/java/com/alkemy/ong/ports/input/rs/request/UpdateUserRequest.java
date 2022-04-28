package com.alkemy.ong.ports.input.rs.request;

import com.alkemy.ong.domain.model.Role;
import lombok.*;

import javax.persistence.*;

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

    private Role role;
}
