package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Testimonial;

public interface TestimonialService {
    void createEntity(Testimonial testimonial);
    void deleteById(Long id);
}
