package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Testimonial;
import java.util.Optional;

public interface TestimonialService {
    Long createEntity(Testimonial testimonial);
    void deleteById(Long id);
    Optional<Testimonial> updateEntityIfExists(Long id, Testimonial entity);
}