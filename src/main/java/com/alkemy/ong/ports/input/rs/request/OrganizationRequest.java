package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequest {

    @NotBlank()
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("image")
    private String image;

    @JsonProperty("phone")
    private Integer phone;

    @Email(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
    @NotBlank()
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("welcome_text")
    private String welcomeText;

    @JsonProperty("about_us_text")
    private String aboutUsText;

    @JsonProperty("address")
    private String address;

    @JsonProperty("facebook_url")
    private String facebookUrl;

    @JsonProperty("linkedin_url")
    private String linkedinUrl;

    @JsonProperty("instagram_url")
    private String instagramUrl;
}
