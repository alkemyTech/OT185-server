package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Testimonial;

public interface TestimonialService {
    Long createEntity(Testimonial testimonial);
    void deleteById(Long id);
    Testimonial updateEntityIfExists(Long id, Testimonial entity);
}