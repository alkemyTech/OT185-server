package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.repository.CommentRepository;
import com.alkemy.ong.domain.repository.NewsRepository;
import com.alkemy.ong.domain.repository.UserRepository;
import com.alkemy.ong.domain.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentJpaRepository;
    private final UserRepository userJpaRepository;
    private final NewsRepository newsJpaRepository;

    @Override
    @Transactional
    public Long createComment(Comment comment) {
        User user = userJpaRepository.findById(comment.getUser().getId())
                .orElseThrow(() -> new NotFoundException(comment.getUser().getId()));
        comment.setUser(user);
        News news = newsJpaRepository.findById(comment.getNews().getId())
                .orElseThrow(() -> new NotFoundException(comment.getNews().getId()));
        comment.setNews(news);
        return commentJpaRepository.save(comment).getId();
    }
}
