package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.COMMENT_URI;

@RestController
@RequestMapping(COMMENT_URI)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@Valid @NotNull @PathVariable Long id, @AuthenticationPrincipal User user) {
        service.deleteById(id, user);
    }




}
