package com.alkemy.ong.ports.input.rs.response;


import lombok.*;


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
