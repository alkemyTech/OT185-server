package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.UserMapper;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserMapper userMapper;
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
                        true, getAuthorities(user.get()));
    }

        public Optional<User> findUserByEmail(String email) throws UsernameNotFoundException {
            return userRepository.findUserByEmail(email);

        }

        public UserResponse meData(String email) {

            Optional<User> user = findUserByEmail(email);

            UserResponse userResponse = userMapper.toDto(user.get());

            return userResponse;
        }

    private static Set<? extends GrantedAuthority> getAuthorities(User user) {

        Role role = user.getRole();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        System.out.println(role.getName());
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }

}

