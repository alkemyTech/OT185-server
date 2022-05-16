package com.alkemy.ong.ports.input.rs.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseList {

	@JsonProperty("content")
	private List<UserResponse> content = null;
	@JsonProperty("nextUri")
	private String nextUri;
	@JsonProperty("previousUri")
	private String previousUri;
	@JsonProperty("totalPages")
	private Integer totalPages;
	@JsonProperty("totalElements")
	private Long totalElements;

}
