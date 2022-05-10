package com.alkemy.ong.ports.input.rs.controller;



import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.usecase.NewsService;
import com.alkemy.ong.ports.input.rs.mapper.NewsControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.NEWS_URI;


@RestController
@RequestMapping(NEWS_URI)
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    private final NewsControllerMapper newsControllerMapper;



    @PostMapping
    public ResponseEntity<Void> createNews(@Valid @RequestBody CreateNewsRequest createNewsRequest) {

        News news = newsControllerMapper.createNewsRequestToNews(createNewsRequest);
        Long CategoryId = createNewsRequest.getCategoryId();

        final long id = service.createEntity(news,CategoryId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@Valid @NotNull @PathVariable Long id) {
        service.deleteById(id);
    }


}
