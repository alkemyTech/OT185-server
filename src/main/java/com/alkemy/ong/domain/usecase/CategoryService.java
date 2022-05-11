package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Category;

import java.util.List;

public interface CategoryService {
    void deleteById(Long id);

    Long createCategory(Category request);

    Category getByIdIfExists(Long id);

    List<Category> getAll();

    Category updateCategory(Long id, Category category);
}

