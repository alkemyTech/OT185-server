package com.alkemy.ong.ports.input.rs.api;

import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.ports.input.rs.request.OrganizationRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateOrganizationRequest;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import com.alkemy.ong.ports.input.rs.response.SlideResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@SecurityRequirement(name = "bearerAuth")
public interface OrganizationApi {

    @Operation(summary = "Get Organization by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Organization", content = {@Content(mediaType = "JSON Value",
                    schema = @Schema(implementation = OrganizationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "JSON Value",
                    examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("Organization ID is not found.")),
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<OrganizationResponse> getOrganization(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "Get List of slides by Organization id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Organization slide list", content = {@Content(mediaType = "JSON Value",
                    schema = @Schema(implementation = SlideResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "JSON Value",
                    examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("Organization ID is not found.")),
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<List<SlideResponse>> getSlideList(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "update organization partially", responses = {
            @ApiResponse(responseCode = "204", description = "Partial update", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("Organization ID is not found.")),
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "JSON Value",
                    examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    void updateOrganization(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateOrganizationRequest updateOrganizationRequest);

    @Operation(summary = "update organization completely or created new organization", responses = {
            @ApiResponse(responseCode = "204", description = "Completely update", content = @Content),
            @ApiResponse(responseCode = "201", description = "Created a new Organization", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "JSON Value",
                    examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<Void> updateOrganization(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody OrganizationRequest organizationRequest);
}
