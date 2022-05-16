package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.UserList;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.UserControllerMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import com.alkemy.ong.ports.input.rs.response.UserResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.USER_URI;

@RestController
@RequestMapping(USER_URI)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final UserControllerMapper userControllerMapper;

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@Valid @NotNull @PathVariable Long id,
			@Valid @RequestBody UpdateUserRequest updateUserRequest) {

		User user = userControllerMapper.updateUserRequestToUser(updateUserRequest);

		userService.updateEntityIfExists(id, user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@Valid @NotNull @PathVariable Long id) {
		userService.deleteUserById(id);

	}

	@GetMapping
	public ResponseEntity<UserResponseList> getUsers(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size) {

		final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
		final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

		UserList list = userService.getList(PageRequest.of(pageNumber, pageSize));
		UserResponseList response;
		{
			response = new UserResponseList();
			List<UserResponse> content = userControllerMapper.userListToUserResponseList(list.getContent());
			response.setContent(content);
			final int nextPage = list.getPageable().next().getPageNumber();
			response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

			final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
			response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

			response.setTotalPages(list.getTotalPages());
			response.setTotalElements(list.getTotalElements());
		}

		return ResponseEntity.ok().body(response);
	}

}
