package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.repository.usecase.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.TESTIMONIAL_URI;

@RestController
@RequestMapping(TESTIMONIAL_URI)
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestimonial(@Valid @NotNull @PathVariable Long id){
        testimonialService.deleteById(id);
    }
}
