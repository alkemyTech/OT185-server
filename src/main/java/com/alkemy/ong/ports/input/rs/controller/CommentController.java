package com.alkemy.ong.ports.input.rs.controller;



import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.CommentService;
import com.alkemy.ong.ports.input.rs.response.CommentResponse;
import lombok.RequiredArgsConstructor;
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

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.COMMENT_URI;


@RestController
@RequestMapping(COMMENT_URI)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;
    private final CommentControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest){
        Comment comment = mapper.createCommentRequestToComment(createCommentRequest);

        final long id = service.createComment(comment);

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
    public ResponseEntity<List<CommentResponse>> getComments(){
        List<Comment> commentList = service.getAll();
        List<CommentResponse> responseList = mapper.commentListToCommentResponseList(commentList);

        return ResponseEntity.ok().body(responseList);
    }
}


