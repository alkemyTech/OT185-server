package com.alkemy.ong.ports.input.rs.request;


import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotBlank
    @JsonProperty("comment_body")
    private String commentBody;

    @JsonProperty("user")
    private User user;

    @JsonProperty("news")
    private News news;

}
