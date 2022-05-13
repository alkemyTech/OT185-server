package com.alkemy.ong.ports.input.rs.api;


import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import com.alkemy.ong.ports.input.rs.response.CommentResponse;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@SecurityRequirement(name = "bearerAuth")
public interface NewsApi {


    @Operation(summary = "Update news by ID", description = "Update all details of the specific news ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News updated",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value="Incorrect parameters in JSON Object"))),
            @ApiResponse(responseCode = "404",description = "Not Found",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("News's ID is not found."))
                    ))
    }
    )
    ResponseEntity<NewsResponse> updateNews(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateNewsRequest updateNewsRequest);

    @Operation(summary = "Get news by ID", description = "Get details of the specific news ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News found",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value="Incorrect parameter (ID)"))),
            @ApiResponse(responseCode = "404",description = "Not Found",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("Id is not found."))
                    ))
    })
    ResponseEntity<NewsResponse> getNew(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "Delete news", description = "Delete news by ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value="Incorrect parameter (ID)"))),
            @ApiResponse(responseCode = "404",description = "Not Found",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value = ("Id is not found."))
                    ))
    }
    )
    void deleteNews(@Valid @NotNull @PathVariable Long id);

    @Operation(summary = "Get news's comments", description = "Get news's comments by ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments found",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value="Incorrect parameter (ID)"))),
    }
    )
    ResponseEntity<List<CommentResponse>> getCommentsByNewsId(@Valid @NotNull @PathVariable Long id);


    @Operation(summary = "Create news", description = "Create news ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "News created",
                    content = { @Content(mediaType = "JSON Value")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "JSON Value",
                            examples = @ExampleObject(value="Incorrect parameters in JSON Object")))}
    )
   ResponseEntity<Void> createNews(@Valid @RequestBody CreateNewsRequest createNewsRequest);

}
