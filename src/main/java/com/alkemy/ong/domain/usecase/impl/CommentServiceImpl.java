package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.User;
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
    public void deleteById(Long id, User user) {
        if (user.getRole().getAuthority() == "ADMIN"){
            commentJpaRepository.deleteById(id);
        }else{
            if(user.getId() == commentJpaRepository.findById(id).get().getUser().getId()){
                commentJpaRepository.deleteById(id);
            }
        }
    }
}
