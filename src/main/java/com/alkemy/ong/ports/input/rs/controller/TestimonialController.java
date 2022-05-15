package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.domain.model.TestimonialList;
import com.alkemy.ong.domain.usecase.TestimonialService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.TestimonialControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @PatchMapping("/{id}")
    public ResponseEntity<TestimonialResponse> updateTestimonial(@Valid @NotNull @PathVariable Long id,
                                                                 @Valid @RequestBody UpdateTestimonialRequest updateTestimonialRequest) {

        Testimonial testimonial = mapper.updateTestimonialResquestToTestimonial(updateTestimonialRequest);

        TestimonialResponse response = mapper.testimonialToTestimonialResponse(
                testimonialService.updateEntityIfExists(id, testimonial));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<TestimonialResponseList> getTestimonials(@RequestParam Optional<Integer> page) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);

        TestimonialList testimonials = testimonialService.getList(PageRequest.of(pageNumber,ApiConstants.DEFAULT_PAGE_SIZE));

        TestimonialResponseList responseList;
        {
            responseList = new TestimonialResponseList();

            List<TestimonialResponse> content = mapper.testimonialListToTestimonialResponseList(testimonials.getContent());
            responseList.setContent(content);

            final int nextPage = testimonials.getPageable().next().getPageNumber();
            responseList.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = testimonials.getPageable().previousOrFirst().getPageNumber();
            responseList.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            responseList.setTotalPages(testimonials.getTotalPages());
            responseList.setTotalElements(testimonials.getTotalElements());
        }

        return ResponseEntity.ok().body(responseList);
    }
}
