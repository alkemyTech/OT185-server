package com.alkemy.ong.domain.usecase;

import org.springframework.data.domain.PageRequest;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.UserList;

public interface UserService {

	void updateEntityIfExists(Long id, User user);

	void deleteUserById(Long id);

	User createUser(User user);

	UserList getList(PageRequest pageRequest);
}
