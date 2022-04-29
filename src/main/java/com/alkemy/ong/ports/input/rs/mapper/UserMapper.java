package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {


     UserResponse usertoUserResponse(User user);

     User updateUserRequestToUser(UpdateUserRequest update);

     UserResponse userToUpdateUserResponse(User user);
}
