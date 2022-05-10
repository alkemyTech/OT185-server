package com.alkemy.ong.ports.input.rs.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsRequest {



    @JsonProperty("name")
    private String name;

    @JsonProperty("content")
    private String content;

    @JsonProperty("image")
    private String image;

    @JoinColumn(name = "category_id")
    private String categoryId;
}
