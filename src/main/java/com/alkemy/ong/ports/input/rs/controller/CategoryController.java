package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.usecase.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CATEGORIES_URI;

@RestController
@RequestMapping(CATEGORIES_URI)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@Valid @NotNull @PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
