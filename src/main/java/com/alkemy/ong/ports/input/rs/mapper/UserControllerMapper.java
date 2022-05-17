package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserControllerMapper {

    User updateUserRequestToUser(UpdateUserRequest userRequest);

    UserResponse userToUserResponse(User user);

    List<UserResponse> userListToUserResponse(List<User> users);
}
