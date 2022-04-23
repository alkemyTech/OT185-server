package com.alkemy.ong.ports.input.rs.response;

import com.alkemy.ong.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

}
