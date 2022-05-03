package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.Comment;

import java.nio.file.AccessDeniedException;

public interface CommentService {

    void deleteById(Long id, User user) throws AccessDeniedException;

    Long createComment(Comment request);


}
