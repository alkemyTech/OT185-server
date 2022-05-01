package com.alkemy.ong.domain.usecase.impl;


import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService, UserService {



    private PasswordEncoder passwordEncoder;
    private final UserRepository userJpaRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userJpaRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name: %s not found".formatted(email)));
    }

    @Override
    @Transactional
    public void updateEntityIfExists(Long id, User user) {
        userJpaRepository.findById(id)
                .map(userJpa -> {
                    Optional.ofNullable(user.getFirstName()).ifPresent(userJpa::setFirstName);
                    Optional.ofNullable(user.getLastName()).ifPresent(userJpa::setLastName);
                    Optional.ofNullable(user.getPhoto()).ifPresent(userJpa::setPhoto);
                    Optional.ofNullable(user.getPassword()).ifPresent(userJpa::setPassword);
                    Optional.ofNullable(user.getRole()).ifPresent(userJpa::setRole);

                    return userJpaRepository.save(userJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }




}

