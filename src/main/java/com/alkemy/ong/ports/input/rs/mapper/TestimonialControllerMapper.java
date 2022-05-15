package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;


@Mapper
public interface TestimonialControllerMapper extends CommonMapper{

    Testimonial createTestimonialRequestToTestimonial(CreateTestimonialRequest createTestimonialRequest);
    Testimonial updateTestimonialResquestToTestimonial(UpdateTestimonialRequest updateTestimonialRequest);

    @Named("testimonialToTestimonialResponse")
    TestimonialResponse testimonialToTestimonialResponse(Testimonial testimonial);

    @IterableMapping(qualifiedByName = "testimonialToTestimonialResponse")
    List<TestimonialResponse> testimonialListToTestimonialResponseList(List<Testimonial> testimonials);
}
