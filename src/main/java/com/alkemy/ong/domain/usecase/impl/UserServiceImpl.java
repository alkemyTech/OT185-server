package com.alkemy.ong.domain.usecase.impl;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.UserMapper;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService, UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final UserRepository userJpaRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userJpaRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name: %s not found".formatted(email)));
    }

        public Optional<User> findUserByEmail(String email) throws UsernameNotFoundException {
            return userRepository.findUserByEmail(email);

        }

        public UserResponse meData(String email) {

            Optional<User> user = findUserByEmail(email);

            UserResponse userResponse = userMapper.toDto(user.get());

            return userResponse;
        }


}

