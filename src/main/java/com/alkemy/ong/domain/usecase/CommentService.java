package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Comment;

public interface CommentService {

    Long createComment(Comment request);

}
