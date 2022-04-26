package com.alkemy.ong.ports.input.rs.response.mapper;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {


     UserResponse toDto(User user);
}
