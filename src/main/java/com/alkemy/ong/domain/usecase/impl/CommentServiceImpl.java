package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.repository.CommentRepository;
import com.alkemy.ong.domain.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentJpaRepository;

    @Override
    @Transactional
    public Long createComment(Comment comment) {
        return commentJpaRepository.save(comment).getId();
    }
}
