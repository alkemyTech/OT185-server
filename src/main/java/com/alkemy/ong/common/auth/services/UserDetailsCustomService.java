package com.alkemy.ong.common.auth.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {

            throw new UsernameNotFoundException("username or password not fount");

        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), true, true, true, true, getAuthorities(user.get().getRoles()));
    }
    }
}
