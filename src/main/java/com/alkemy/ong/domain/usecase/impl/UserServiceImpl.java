package com.alkemy.ong.domain.usecase.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.common.exception.ConflictException;
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

	private final PasswordEncoder passwordEncoder;

	private static final Long ROLE_ADMIN_ID = (long) 1;
	private static final Long ROLE_USER_ID = (long) 2;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userJpaRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User name: %s not found".formatted(email)));
	}

	@Override
	@Transactional
	public User createUser(User user) {
		if (existsByEmail(user.getEmail())) {
			throw new ConflictException("Email address '%s' already exists".formatted(user.getEmail()));
		}
		Role role = roleJpaRepository.findById(ROLE_USER_ID).orElseThrow(() -> new NotFoundException(ROLE_USER_ID));
		user.setRole(role);
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		return userJpaRepository.save(user);
	}

	@Override
	@Transactional
	public void updateEntityIfExists(Long id, User user) {
		userJpaRepository.findById(id).map(userJpa -> {
			Optional.ofNullable(user.getFirstName()).ifPresent(userJpa::setFirstName);
			Optional.ofNullable(user.getLastName()).ifPresent(userJpa::setLastName);
			Optional.ofNullable(user.getPhoto()).ifPresent(userJpa::setPhoto);
			Optional.ofNullable(user.getRole()).ifPresent(userJpa::setRole);

			if (user.getPassword() != null) {
				String encoded = passwordEncoder.encode(user.getPassword());
				userJpa.setPassword(encoded);
			}

			return userJpaRepository.save(userJpa);
		}).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		userJpaRepository.findById(id).ifPresent(userJpaRepository::delete);
	}

	private boolean existsByEmail(String email) {
		return userJpaRepository.findUserByEmail(email).isPresent();
	}

}
