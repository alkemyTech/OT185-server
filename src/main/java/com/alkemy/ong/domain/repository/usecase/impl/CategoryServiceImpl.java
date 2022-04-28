package com.alkemy.ong.domain.repository.usecase.impl;

import com.alkemy.ong.domain.repository.CategoryRepository;
import com.alkemy.ong.domain.repository.usecase.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryJpaRepository;

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryJpaRepository.findById(id).ifPresent(categoryJpaRepository::delete);
    }

}

