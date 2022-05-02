package com.alkemy.ong.ports.input.rs.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

	@NotNull
	@JsonProperty("first_name")
	private String firstName;

	@NotNull
	@JsonProperty("last_name")
	private String lastName;

	@Email
	@NotNull
	@JsonProperty("email")
	private String email;

	@NotNull
	@JsonProperty("email")
	@Size(min = 8, max = 12)
	private String password;
}
