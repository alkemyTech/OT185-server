package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.UserMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
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
    private final UserRepository userJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Optional<User> user = userJpaRepository.findUserByEmail(email);

        if (user.isEmpty()) {

            throw new UsernameNotFoundException("username or password not fount".formatted(email));

        }

        return new org.springframework.security.core.userdetails.User
                (user.get().getUsername(), user.get().getPassword(), true, true, true,
                        true, getAuthorities(user.get()));
    }

        private Optional<User> findUserById(Long id) throws UsernameNotFoundException {
            return userJpaRepository.findById(id);

        }

        public UserResponse meData(String email) {

            Optional<User> user = userJpaRepository.findUserByEmail(email);

            UserResponse userResponse = userMapper.usertoUserResponse(user.get());

            return userResponse;
        }


    @Override
    @Transactional
    public void updateEntityIfExists(Long id, UpdateUserRequest updateUserRequest) {
        User user = userMapper.updateUserRequestToUser(updateUserRequest);
        findUserById(id)
                .map(userJpa -> {
                    Optional.ofNullable(user.getEmail()).ifPresent(userJpa::setEmail);
                    Optional.ofNullable(user.getFirstName()).ifPresent(userJpa::setFirstName);
                    Optional.ofNullable(user.getLastName()).ifPresent(userJpa::setLastName);
                    Optional.ofNullable(user.getPhoto()).ifPresent(userJpa::setPhoto);
                    Optional.ofNullable(user.getRole()).ifPresent(userJpa::setRole);
                    Optional.ofNullable(user.getPassword()).ifPresent(userJpa::setPassword);

                    return userMapper.userToUpdateUserResponse(userJpaRepository.save(userJpa));
                }).orElseThrow(() -> new NotFoundException(id));
    }



    @Override
    @Transactional
    public void deleteUserById(Long id) {

        findUserById(id).ifPresent(userJpaRepository::delete);
    }




    private static Set<? extends GrantedAuthority> getAuthorities(User user) {

        Role role = user.getRole();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        System.out.println(role.getName());
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }

}

