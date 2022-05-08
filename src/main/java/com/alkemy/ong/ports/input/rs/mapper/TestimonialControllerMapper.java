package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateTestimonialRequest;
import com.alkemy.ong.ports.input.rs.response.TestimonialResponse;
import org.mapstruct.Mapper;

import java.util.Optional;


@Mapper
public interface TestimonialControllerMapper extends CommonMapper{

    Testimonial createTestimonialRequestToTestimonial(CreateTestimonialRequest createTestimonialRequest);
    Testimonial updateTestimonialResquestToTestimonial(UpdateTestimonialRequest updateTestimonialRequest);
    //TestimonialResponse optionalTestimonialToTestimonialresponse(Optional<Testimonial> testimonial);
    TestimonialResponse testimonialToTestimonialResponse(Testimonial testimonial);
}
