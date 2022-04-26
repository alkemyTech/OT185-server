package com.alkemy.ong.ports.input.rs.api;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

public interface ApiConstants {

    String ALKYMERS_URI = "/v1/alkymers";
    String ORGANIZATIONS_URI = "/v1/organizations";
    String MEMBERS_URI = "/v1/members";
    String SLIDES_URI = "/v1/slides";
    String TESTIMONIAL_URI = "/v1/testimonials";
    String CATEGORIES_URI = "/v1/categories";

    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = (page) ->
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .replaceQueryParam("page", page).toUriString();

}
