package com.alkemy.ong.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlkymerResponseList {
    @JsonProperty("content")
    private List<AlkymerResponse> content = null;
    @JsonProperty("nextUri")
    private String nextUri;
    @JsonProperty("previousUri")
    private String previousUri;
    @JsonProperty("totalPages")
    private Integer totalPages;
    @JsonProperty("totalElements")
    private Long totalElements;
}
