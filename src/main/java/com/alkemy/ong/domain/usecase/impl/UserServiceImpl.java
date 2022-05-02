package com.alkemy.ong.domain.usecase.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.common.exception.BadRequestException;
import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.RoleRepository;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.UserService;

import lombok.RequiredArgsConstructor;

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
	public User createUser(User user) {
		if (existsByEmail(user.getEmail())) {
			throw new BadRequestException("The %s email address already exists".formatted(user.getEmail()));
		}
		Role role = roleJpaRepository.findById((long) 2).orElseThrow(() -> new NotFoundException(2));
		user.setRole(role);
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		return userJpaRepository.save(user);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userJpaRepository.findUserByEmail(email).isPresent();
	}

}
