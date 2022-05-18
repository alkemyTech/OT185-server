package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.NewsList;
import com.alkemy.ong.domain.usecase.NewsService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.api.NewsApi;
import com.alkemy.ong.ports.input.rs.mapper.CommentControllerMapper;
import com.alkemy.ong.ports.input.rs.mapper.NewsControllerMapper;
import com.alkemy.ong.ports.input.rs.response.*;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.net.URI;
import java.util.Optional;


import static com.alkemy.ong.ports.input.rs.api.ApiConstants.NEWS_URI;


@RestController
@RequestMapping(NEWS_URI)
@RequiredArgsConstructor
public class NewsController implements NewsApi {

    private final NewsService service;

    private final NewsControllerMapper newsControllerMapper;

    private final CommentControllerMapper commentControllerMapper;


    @GetMapping
    public ResponseEntity<NewsResponseList> getNews(@RequestParam Optional<Integer> page,
                                                    @RequestParam Optional<Integer> size) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        NewsList list = service.getList(PageRequest.of(pageNumber, pageSize));

        NewsResponseList response;
        {
            response = new NewsResponseList();

            List<NewsResponse> content = newsControllerMapper.newsListToNewsResponseList(list.getContent());
            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());
        }
        return ResponseEntity.ok().body(response);
    }





    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateNewsRequest updateNewsRequest) {

        News news = newsControllerMapper.updateNewsRequestToNews(updateNewsRequest);
        Long categoryId = updateNewsRequest.getCategoryId();
        NewsResponse response = newsControllerMapper.newsToNewsResponse(service.updateEntityIfExists(id, news, categoryId));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@Valid @NotNull @PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNew(@Valid @NotNull @PathVariable Long id){
        News news = service.getByIdIfExists(id);
        NewsResponse response = newsControllerMapper.newsToNewsResponse(news);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByNewsId(@Valid @NotNull @PathVariable Long id){
        List<Comment> comments = service.getCommentsByNewsId(id);
        List<CommentResponse> responseList = commentControllerMapper.commentListToCommentResponseList(comments);

        return ResponseEntity.ok().body(responseList);
    }

    @PostMapping
    public ResponseEntity<Void> createNews(@Valid @RequestBody CreateNewsRequest createNewsRequest) {

        News news = newsControllerMapper.createNewsRequestToNews(createNewsRequest);
        Long categoryId = createNewsRequest.getCategoryId();

        final long id = service.createEntity(news, categoryId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
