package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface TestimonialControllerMapper extends CommonMapper{

    @Named("testimonialToTestimonialResponse")
    TestimonialResponse testimonialToTestimonialResponse (Testimonial testimonial);

    Testimonial createTestimonialRequestToTestimonial(CreateTestimonialRequest createTestimonialRequest);
}
