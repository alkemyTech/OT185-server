package com.alkemy.ong.domain.repository.usecase.impl;

import com.alkemy.ong.domain.repository.CommentRepository;
import com.alkemy.ong.domain.repository.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentJpaRepository;
}
