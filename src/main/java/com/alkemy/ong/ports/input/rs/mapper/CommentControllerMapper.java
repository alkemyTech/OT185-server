package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCommentRequest;
import com.alkemy.ong.ports.input.rs.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper

public interface CommentControllerMapper extends CommonMapper{

    @Mapping(source = "newsId", target = "news.id")
    Comment createCommentRequestToComment(CreateCommentRequest request);

    Comment updateCommentRequestToComment(UpdateCommentRequest updateCommentRequest);

    List<CommentResponse> commentListToCommentResponseList(List<Comment> commentList);
}
