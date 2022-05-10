package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.User;

import java.util.List;

public interface UserService {

	void updateEntityIfExists(Long id, User user);

	void deleteUserById(Long id);

	User createUser(User user);

	List<User> getAll();
}
