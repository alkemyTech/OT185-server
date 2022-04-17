package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.usecase.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //TODO
        /*
          User user = findByEmail(email);

         if(user == null)
          throw new NotFoundExceptions("Nombre de usuario incorrecto");

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
