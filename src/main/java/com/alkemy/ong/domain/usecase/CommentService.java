package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.User;

public interface CommentService {

    Long createComment(Comment request);




    public Comment updateCommentIfExists(Long id, Comment commentUpdate, User user);

}
