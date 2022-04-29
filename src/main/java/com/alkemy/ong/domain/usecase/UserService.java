package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {



   UserResponse meData(String email);



   void deleteUserById(Long id);


}
