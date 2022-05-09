package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.domain.usecase.SlideService;
import com.alkemy.ong.ports.input.rs.mapper.SlideControllerMapper;
import com.alkemy.ong.ports.input.rs.request.SlideRequest;
import com.alkemy.ong.ports.input.rs.response.SlideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.SLIDES_URI;

@RestController
@RequestMapping(SLIDES_URI)
@RequiredArgsConstructor
public class SlideController {

    private final SlideService slideService;
    private final SlideControllerMapper mapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSlide(@Valid @NotNull @PathVariable Long id) {
        slideService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponse> getSlide(@Valid @NotNull @PathVariable Long id) {
        Slide slide = slideService.getById(id);
        SlideResponse slideResponse = mapper.slideToSlideResponse(slide);
        return ResponseEntity.ok(slideResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createSlide(@RequestBody SlideRequest slideRequest) {
        Slide slide = mapper.createSlideRequestToSlide(slideRequest);
        final long id = slideService.create(slide);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
