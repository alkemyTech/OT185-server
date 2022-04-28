package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.repository.usecase.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
}
