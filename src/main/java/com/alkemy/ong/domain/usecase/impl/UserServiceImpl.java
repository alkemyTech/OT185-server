package com.alkemy.ong.domain.usecase.impl;


import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.RoleRepository;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService, UserService {




    private final UserRepository userJpaRepository;
    
    private final RoleRepository roleJpaRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userJpaRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name: %s not found".formatted(email)));
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userJpaRepository.findById(id).ifPresent(userJpaRepository::delete);
    }


	@Override
	@Transactional
	public Long createUser(User user) {
		
		Role role = roleJpaRepository.findById((long) 2).orElseThrow(() -> new NotFoundException(1));
		user.setRole(role);
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		return userJpaRepository.save(user).getId();
	}

}

