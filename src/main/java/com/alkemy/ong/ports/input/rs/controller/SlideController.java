package com.alkemy.ong.ports.input.rs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.domain.repository.usecase.SlideService;
import lombok.RequiredArgsConstructor;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.SLIDES_URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(SLIDES_URI)
@RequiredArgsConstructor
public class SlideController {

	private final SlideService slideService;

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSlide(@Valid @NotNull @PathVariable Long id) {
		slideService.deleteById(id);
	}

}
