package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.Comment;

public interface CommentService {

    void deleteById(Long id, User user);

    Long createComment(Comment request);


}
