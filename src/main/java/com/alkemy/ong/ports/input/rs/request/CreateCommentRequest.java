package com.alkemy.ong.ports.input.rs.request;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotBlank
    @JsonProperty("comment_body")
    private String commentBody;

    @JsonProperty("news_id")
    private Long newsId;

}
