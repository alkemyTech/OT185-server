package com.alkemy.ong.ports.input.rs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.domain.usecase.MemberService;
import lombok.RequiredArgsConstructor;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.MEMBERS_URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(MEMBERS_URI)
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@Valid @NotNull @PathVariable Long id) {
		memberService.deleteById(id);
	}
}
