package com.alkemy.ong.ports.input.rs.api;

import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCommentRequest;
import com.alkemy.ong.ports.input.rs.response.CommentResponse;
import com.alkemy.ong.ports.input.rs.response.CommentResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
@SecurityRequirement(name = "bearerAuth")
public interface CommentApi {
    @Parameter(name = "user", hidden = true)
    @Operation(summary = "create a new comment", responses = {
            @ApiResponse(responseCode = "201", description = "comment created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<Void> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest, @AuthenticationPrincipal User user);

    @Parameter(name = "user", hidden = true)
    @Operation(summary = "delete comment", description = "delete a comment", responses = {
            @ApiResponse(responseCode = "200", description = "comment deleted ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid User or Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    void deleteComment(@Valid @NotNull @PathVariable Long id, @AuthenticationPrincipal User user);

    @Parameter(name = "user", hidden = true)
    @Operation(summary = "update comment", description = "update a comment completely", responses = {
            @ApiResponse(responseCode = "200", description = "comment updated ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid User or Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    void updateComment(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateCommentRequest updateCommentRequest, @AuthenticationPrincipal User user);

    @Operation(summary = "get comments", description = "get all comments sorted by creation date", responses = {
            @ApiResponse(responseCode = "200", description = "get comments ",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CommentResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<CommentResponseList> getComments(@RequestParam Optional<Integer> page,
                                                    @RequestParam Optional<Integer> size);
}
