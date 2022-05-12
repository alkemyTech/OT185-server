package com.alkemy.ong.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("phone")
    private Integer phone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("facebook_url")
    private String facebookUrl;

    @JsonProperty("linkedin_url")
    private String linkedinUrl;

    @JsonProperty("instagram_url")
    private String instagramUrl;

}
