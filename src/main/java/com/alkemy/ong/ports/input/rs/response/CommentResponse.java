package com.alkemy.ong.ports.input.rs.response;


import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("comment_body")
    private String commentBody;

    @JsonProperty("user")
    private User user;

    @JsonProperty("news")
    private News news;
}
