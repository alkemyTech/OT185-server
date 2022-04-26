package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Testimonial;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestimonialRepository extends PagingAndSortingRepository<Testimonial, Long> {
}
