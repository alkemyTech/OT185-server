package com.alkemy.ong.ports.input.rs.controller;



import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.CommentList;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.CommentService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.response.CommentResponse;
import com.alkemy.ong.ports.input.rs.response.CommentResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.alkemy.ong.ports.input.rs.mapper.CommentControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCommentRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.COMMENT_URI;


@RestController
@RequestMapping(COMMENT_URI)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;
    private final CommentControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest, @AuthenticationPrincipal User user){
        Comment comment = mapper.createCommentRequestToComment(createCommentRequest);

        final long id = service.createComment(comment, user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@Valid @NotNull @PathVariable Long id, @AuthenticationPrincipal User user) {
        service.deleteById(id, user);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateCommentRequest updateCommentRequest, @AuthenticationPrincipal User user) {
        Comment comment = mapper.updateCommentRequestToComment(updateCommentRequest);
        service.updateCommentIfExists(id, comment, user);
    }


    @GetMapping
    public ResponseEntity<CommentResponseList> getComments(@RequestParam Optional<Integer> page,
                                                             @RequestParam Optional<Integer> size){

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        CommentList commentList = service.getAll(PageRequest.of(pageNumber,pageSize));

        CommentResponseList responseList;
        {
            responseList = new CommentResponseList();

            List<CommentResponse> content = mapper.commentListToCommentResponseList(commentList.getContent());
            responseList.setContent(content);

            final int nextPage = commentList.getPageable().next().getPageNumber();
            responseList.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = commentList.getPageable().previousOrFirst().getPageNumber();
            responseList.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            responseList.setTotalPages(commentList.getTotalPages());
            responseList.setTotalElements(commentList.getTotalElements());
        }


        return ResponseEntity.ok().body(responseList);
    }

}


