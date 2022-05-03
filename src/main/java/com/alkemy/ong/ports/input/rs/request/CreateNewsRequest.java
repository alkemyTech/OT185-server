package com.alkemy.ong.ports.input.rs.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewsRequest {

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("content")
    private String content;

    @NotBlank
    @JsonProperty("image")
    private String image;

    @NotNull
    @JsonProperty("category_id")
    private Long categoryId;
}
