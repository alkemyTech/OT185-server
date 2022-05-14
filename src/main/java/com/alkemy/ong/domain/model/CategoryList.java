package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CategoryList extends PageImpl<Category> {
    public CategoryList(List<Category> content, Pageable pageable, long total){super(content, pageable, total);}
}
