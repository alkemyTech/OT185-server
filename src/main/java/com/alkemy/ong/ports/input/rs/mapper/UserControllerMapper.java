package com.alkemy.ong.ports.input.rs.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;

@Mapper
public interface UserControllerMapper {

	User updateUserRequestToUser(UpdateUserRequest userRequest);

	@Named("userToUserResponse")
	UserResponse userToUserResponse(User user);

	@IterableMapping(qualifiedByName = "userToUserResponse")
	List<UserResponse> userListToUserResponseList(List<User> users);
}
