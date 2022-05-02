package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.ports.input.rs.request.CategoryRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryControllerMapper extends CommonMapper {

    Category categoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse categoryToCategoryResponse(Category category);

}
