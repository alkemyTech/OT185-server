package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.User;

public interface UserService {

    void updateEntityIfExists(Long id, User user);



}
