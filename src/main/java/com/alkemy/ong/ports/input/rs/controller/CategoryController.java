package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.usecase.CategoryService;
import com.alkemy.ong.ports.input.rs.mapper.CategoryControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CategoryRequest;
import com.alkemy.ong.ports.input.rs.response.CategoryNameResponseList;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CATEGORIES_URI;

@RestController
@RequestMapping(CATEGORIES_URI)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryControllerMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@Valid @NotNull @PathVariable Long id){
        Category category = categoryService.getByIdIfExists(id);
        CategoryResponse response = mapper.categoryToCategoryResponse(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryNameResponseList>> getCategories(){
        List<Category> categories = categoryService.getAll();
        List<CategoryNameResponseList> responses = mapper.categoryListToCategoryResponse(categories);

        return ResponseEntity.ok().body(responses);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){

        Category category = mapper.categoryRequestToCategory(categoryRequest);

        final long id = categoryService.createCategory(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @NotNull @PathVariable Long id,
                                                           @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = mapper.categoryRequestToCategory(categoryRequest);
        CategoryResponse response = mapper.categoryToCategoryResponse(categoryService.updateCategory(id, category));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@Valid @NotNull @PathVariable Long id) {
        categoryService.deleteById(id);
    }

}
