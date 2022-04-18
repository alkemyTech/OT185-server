package com.alkemy.ong.domain.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //TODO
/*
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(new UsernameNotFoundException(email));

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
                .forEach
                        (roles -> authorities.add(new SimpleGrantedAuthority(roles.getAuthority())));

        UserDetails userDetail = new User(user.getUserName(), user.getPassword(), authorities);

        return userDetail;
*/
        return null;
    }


}
