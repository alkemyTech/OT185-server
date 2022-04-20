package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
