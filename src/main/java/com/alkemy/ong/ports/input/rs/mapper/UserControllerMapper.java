package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserControllerMapper {

    User updateUserRequestToUser(UpdateUserRequest userRequest);

    UserResponse userToUserResponse(User user);
}
