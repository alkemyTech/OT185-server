package com.alkemy.ong.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("facebook_url")
    private String facebookUrl;

    @JsonProperty("instagram_url")
    private String instagramUrl;

    @JsonProperty("linkedin_url")
    private String linkedinUrl;

    @JsonProperty("image")
    private String image;

    @JsonProperty("description")
    private String description;
}
