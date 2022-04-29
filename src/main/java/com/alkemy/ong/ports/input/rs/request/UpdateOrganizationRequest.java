package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrganizationRequest {

    @JsonProperty("facebook_url")
    private String facebookUrl;

    @JsonProperty("linkedin_url")
    private String linkedinUrl;

    @JsonProperty("instagram_url")
    private String instagramUrl;
}
