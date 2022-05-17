package com.alkemy.ong.ports.input.rs.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsRequest {

    @JsonProperty("name")
    @NotBlank(message = "The name must not be empty")
    private String name;

    @JsonProperty("content")
    @NotBlank(message = "The content must not be empty")
    private String content;

    @JsonProperty("image")
    @NotBlank(message = "The image must not be empty")
    private String image;

    @JoinColumn(name = "category_id")
    private String categoryId;
}
