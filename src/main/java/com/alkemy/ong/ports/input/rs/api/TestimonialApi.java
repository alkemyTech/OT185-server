package com.alkemy.ong.ports.input.rs.api;

import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            @ApiResponse(responseCode = "201", description = "Testimonial created",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))})
    })
    ResponseEntity<Void> createTestimonial(@Valid @RequestBody CreateTestimonialRequest createTestimonialRequest);

    @Operation(summary = "Delete Testimonial", description = "Delete Testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Incorrect Parameter (ID)"))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Id is not found."))})
    })
    void deleteTestimonial(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "Update Testimonial", description = "Update Testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonial Updated",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Id is not found."))})
    })
    ResponseEntity<TestimonialResponse> updateTestimonial(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateTestimonialRequest updateTestimonialRequest);

    @Operation(summary = "Get Testimonials", description = "Get a paged list of testimonials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonials paged list retrieval succesful",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = "Incorrect parameters in JSON Object"))})
    })
    ResponseEntity<TestimonialResponseList> getTestimonials(@RequestParam Optional<Integer> page);
}
