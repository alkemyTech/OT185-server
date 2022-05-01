package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import org.mapstruct.Mapper;

@Mapper
public interface CommentControllerMapper extends CommonMapper{

    Comment createCommentRequestToComment(CreateCommentRequest request);
}
