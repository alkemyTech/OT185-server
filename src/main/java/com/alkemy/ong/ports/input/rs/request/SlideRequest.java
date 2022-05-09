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
public class SlideRequest {

    @NotBlank
    @JsonProperty("image_base64")
    private String imageBase64;

    @NotBlank
    @JsonProperty("text")
    private String text;

    @JsonProperty("slide_order")
    private Integer order;

    @NotNull
    @JsonProperty("organization_id")
    private Long organization;
}
