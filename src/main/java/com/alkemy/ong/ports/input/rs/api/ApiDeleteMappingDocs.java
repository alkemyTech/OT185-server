package com.alkemy.ong.ports.input.rs.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

@RequestMapping(method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

@ApiPutMappingDocs
@ApiResponse(responseCode = "204", description = "deleted resource ")

public @interface ApiDeleteMappingDocs {
}
