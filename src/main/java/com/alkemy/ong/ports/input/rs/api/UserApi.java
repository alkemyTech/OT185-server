package com.alkemy.ong.ports.input.rs.api;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Validated
@SecurityRequirement(name = "bearerAuth")
public interface UserApi {

	@Operation(summary = "Soft deletes an user by id", responses = {
			@ApiResponse(description = "User deleted", responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "401", description = "Invalid token or token expired", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid Role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	void deleteUserById(@Valid @NotNull @PathVariable Long id);

	@Operation(summary = "Updates an user by id", responses = {
			@ApiResponse(description = "User updated", responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "401", description = "Invalid token or token expired", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid Role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	void updateUser(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest);

	@Operation(summary = "Get a list paginated of all users", responses = {
			@ApiResponse(description = "Users list paginated", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseList.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "401", description = "Invalid token or token expired", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid Role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	ResponseEntity<UserResponseList> getUsers(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size);

}
