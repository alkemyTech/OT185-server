package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.User;

public interface UserService {

    void deleteUserById(Long id);

    Long createUser(User user);

}
