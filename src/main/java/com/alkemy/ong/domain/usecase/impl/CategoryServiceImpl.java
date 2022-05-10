package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.repository.CategoryRepository;
import com.alkemy.ong.domain.usecase.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryJpaRepository;

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryJpaRepository.findById(id).ifPresent(categoryJpaRepository::delete);
    }

    @Override
    @Transactional
    public Long createCategory(Category request) {
        return categoryJpaRepository.save(request).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getByIdIfExists(Long id) {
        return categoryJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return (List<Category>)categoryJpaRepository.findAll();
    }

    @Transactional
    public Category updateCategory(Long id, Category request) {

        Optional<Category> op = categoryJpaRepository.findById(id);
        if(op.isPresent()) {
            Category category = op.get();
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setImage(request.getImage());
            return categoryJpaRepository.save(category);
        }
        return categoryJpaRepository.save(request);
    }

}

