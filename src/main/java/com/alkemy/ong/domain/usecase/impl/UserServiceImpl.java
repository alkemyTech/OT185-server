package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {

            throw new UsernameNotFoundException("username or password not fount".formatted(email));

        }

        return new org.springframework.security.core.userdetails.User
                (user.get().getUsername(), user.get().getPassword(), true, true, true,
                        true, user.get().getAuthorities());

    }
}

