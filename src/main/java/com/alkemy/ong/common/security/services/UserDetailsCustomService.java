package com.alkemy.ong.common.security.services;

import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.RoleRepository;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {

            throw new UsernameNotFoundException("username or password not fount");

        }


        return new org.springframework.security.core.userdetails.User
                (user.get().getUsername(), user.get().getPassword(), true, true, true,
                        true, getAuthorities(user.get()));

    }




    private static Set<? extends GrantedAuthority> getAuthorities(User user) {

        Role role = user.getRole();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        System.out.println(role.getName());
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }

}