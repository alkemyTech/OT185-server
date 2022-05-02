package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Category;

public interface CategoryService {
    void deleteById(Long id);

    Long createCategory(Category request);
}
