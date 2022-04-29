package com.alkemy.ong.ports.input.rs.api.ApiResponsesBody;

import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

@ApiResponse(responseCode = "200", description = "OK",
        content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = OrganizationResponse.class))})

public @interface ApiOrganizationResponseDocs {
}
