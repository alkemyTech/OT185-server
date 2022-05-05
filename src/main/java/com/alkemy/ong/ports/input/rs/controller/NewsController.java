package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.usecase.NewsService;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.NEWS_URI;


@RestController
@RequestMapping(NEWS_URI)
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@Valid @NotNull @PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNew(@Valid @NotNull @PathVariable Long id){
        News news = service.getByIdIfExists(id);
        NewsResponse response = mapper.categoryToCategoryResponse(news);
        return ResponseEntity.ok(response);
    }
}
