package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper

public interface CommentControllerMapper extends CommonMapper{

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "newsId", target = "news.id")

    Comment createCommentRequestToComment(CreateCommentRequest request);
}
