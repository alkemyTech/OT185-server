package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.domain.usecase.TestimonialService;
import com.alkemy.ong.ports.input.rs.mapper.TestimonialControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.TESTIMONIAL_URI;

@RestController
@RequestMapping(TESTIMONIAL_URI)
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;
    private final TestimonialControllerMapper mapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestimonial(@Valid @NotNull @PathVariable Long id){
        testimonialService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createTestimonial(@Valid @RequestBody CreateTestimonialRequest createTestimonialRequest) {

        Testimonial testimonial = mapper.createTestimonialRequestToTestimonial(createTestimonialRequest);

        final long id = testimonialService.createEntity(testimonial);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
