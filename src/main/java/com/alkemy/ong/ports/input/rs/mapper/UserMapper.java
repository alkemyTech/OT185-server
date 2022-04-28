package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.UpdateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {


     UserResponse usertoUserResponse(User user);

     User updateUserRequestToUser(UpdateUserRequest update);

     UserResponse userToUpdateUserResponse(User user);
}
