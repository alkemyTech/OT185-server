package com.alkemy.ong.ports.input.rs.api;

import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.MemberResponse;
import com.alkemy.ong.ports.input.rs.response.MemberResponseList;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
@SecurityRequirement(name = "bearerAuth")
public interface TestimonialApi {

    @Operation(summary = "Create Testimonial", description = "Create Testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Testimonial created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<Void> createTestimonial(@Valid @RequestBody CreateTestimonialRequest createTestimonialRequest);

    @Operation(summary = "Delete Testimonial", description = "Delete Testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    void deleteTestimonial(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "Update Testimonial", description = "Update Testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonial Updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<TestimonialResponse> updateTestimonial(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateTestimonialRequest updateTestimonialRequest);

    @Operation(summary = "Get Testimonials", description = "Get a paged list of testimonials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonials paged list retrieval succesful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponseList.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<TestimonialResponseList> getTestimonials(@RequestParam Optional<Integer> page);
}
