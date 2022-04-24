package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import com.alkemy.ong.ports.input.rs.response.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;



    public Optional<User> findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);

    }

    public UserResponse meData(String email) {

        Optional<User> user = findUserByEmail(email);

        UserResponse userResponse = userMapper.toDto(user.get());

        return userResponse;
    }
}

