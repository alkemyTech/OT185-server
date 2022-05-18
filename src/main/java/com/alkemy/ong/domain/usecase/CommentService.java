package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.CommentList;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.Comment;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface CommentService {

    void deleteById(Long id, User user);

    Long createComment(Comment request, User user);

    void updateCommentIfExists(Long id, Comment commentUpdate, User user);

    CommentList getAll(PageRequest pageRequest);

}
