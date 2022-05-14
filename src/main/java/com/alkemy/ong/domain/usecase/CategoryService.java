package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.CategoryList;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    void deleteById(Long id);

    Long createCategory(Category request);

    Category getByIdIfExists(Long id);

    CategoryList getList(PageRequest pageRequest);

    Category updateCategory(Long id, Category category);
}

