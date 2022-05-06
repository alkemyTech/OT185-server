package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.ports.input.rs.request.CreateTestimonialRequest;
import org.mapstruct.Mapper;


@Mapper
public interface TestimonialControllerMapper extends CommonMapper{

    Testimonial createTestimonialRequestToTestimonial(CreateTestimonialRequest createTestimonialRequest);
}
