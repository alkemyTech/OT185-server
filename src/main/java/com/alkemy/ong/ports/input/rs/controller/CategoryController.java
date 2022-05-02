package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.usecase.CategoryService;
import com.alkemy.ong.ports.input.rs.mapper.CategoryControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CATEGORIES_URI;

@RestController
@RequestMapping(CATEGORIES_URI)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){

        Category category = mapper.categoryRequestToCategory(categoryRequest);

        final long id = categoryService.createCategory(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@Valid @NotNull @PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
