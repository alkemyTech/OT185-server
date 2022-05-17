package com.alkemy.ong.ports.input.rs.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("text")
    private String text;

    @JsonProperty("number")
    private String number;

    @JsonProperty("organization_id")
    private OrganizationResponse organization;

}
