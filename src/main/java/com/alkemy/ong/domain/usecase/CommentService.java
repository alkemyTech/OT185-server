package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.Comment;

import java.util.List;


public interface CommentService {

    void deleteById(Long id, User user);

    Long createComment(Comment request);

    void updateCommentIfExists(Long id, Comment commentUpdate, User user);


    List<Comment> getAll();

}
