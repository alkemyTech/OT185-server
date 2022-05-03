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

import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    @Transactional
    public void deleteById(Long id, User user) throws AccessDeniedException {

        Optional<Comment> op = commentJpaRepository.findById(id);

        if(op.isPresent()){
            Comment comment = op.get();

            boolean canDelete = Objects.equals(user.getRole().getAuthority(), "ADMIN")
                                || Objects.equals(user.getId(), comment.getUser().getId());
            if(canDelete){
                commentJpaRepository.delete(comment);
            }else {
                throw new AccessDeniedException("User not authorized to delete this comment");
            }
        }
    }
}
