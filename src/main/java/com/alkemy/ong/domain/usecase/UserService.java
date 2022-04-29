package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {

   Optional<User> findUserByEmail(String email) throws UsernameNotFoundException;

   UserResponse meData(String email);



}
