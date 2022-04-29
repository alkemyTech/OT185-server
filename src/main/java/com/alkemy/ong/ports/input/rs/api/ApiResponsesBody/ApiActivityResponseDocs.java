package com.alkemy.ong.ports.input.rs.api.ApiResponsesBody;

import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
 * annotation to document the body returned by the PUT method
 */

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

@ApiResponse(responseCode = "200", description = "update resource ",
        content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ActivityResponse.class))})

public @interface ApiActivityResponseDocs {
}
