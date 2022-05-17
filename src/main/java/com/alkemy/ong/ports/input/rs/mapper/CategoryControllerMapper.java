package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.ports.input.rs.request.CategoryRequest;
import com.alkemy.ong.ports.input.rs.response.CategoryNameResponseList;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryControllerMapper extends CommonMapper {

    Category categoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse categoryToCategoryResponse(Category category);

    CategoryNameResponseList categoryToCategoryNameResponse(Category category);

    List<CategoryNameResponseList> categoryListToCategoryResponse(List<Category> categories);
}
