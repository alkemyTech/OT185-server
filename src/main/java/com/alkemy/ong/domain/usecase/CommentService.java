package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.User;

public interface CommentService {

    void deleteById(Long id, User user);

}
