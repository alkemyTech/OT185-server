package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.NewsService;
import com.alkemy.ong.ports.input.rs.mapper.NewsControllerMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
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

    private final NewsControllerMapper newsControllerMapper;
    @PatchMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateNewsRequest updateNewsRequest) {

        News news = newsControllerMapper.updateNewsRequestToNews(updateNewsRequest);
        NewsResponse response = newsControllerMapper.newsToNewsResponse(service.updateEntityIfExists(id, news));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@Valid @NotNull @PathVariable Long id) {
        service.deleteById(id);
    }
}
