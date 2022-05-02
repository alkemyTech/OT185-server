package com.alkemy.ong.ports.input.rs.api;

import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IActivitySwagger {


    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "create a new resource", responses = {
            @ApiResponse(responseCode = "200", description = "resource created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    public ResponseEntity<Void> createActivity(@Valid @RequestBody ActivityRequest activityRequest);


    @Operation(summary = "update new resource", description = "update a resource completely", responses = {
            @ApiResponse(responseCode = "200", description = "update resource ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActivityResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    public ResponseEntity<ActivityResponse> updateActivity(@Valid @NotNull @PathVariable Long id,
                                                           @Valid @RequestBody ActivityRequest activityRequest);

}
