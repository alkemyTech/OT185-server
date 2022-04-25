package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
