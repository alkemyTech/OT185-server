package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.CreateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticationControllerMapper extends CommonMapper{


     UserResponse toDto(User user);
     User createUserRequestToUser(CreateUserRequest user);
}
