package com.alkemy.ong.ports.input.rs.response;

<<<<<<< HEAD
import lombok.*;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;

=======
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

    private String rol;
>>>>>>> feature/OT185-32

}
