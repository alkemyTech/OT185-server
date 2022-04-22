package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Testimonial;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends PagingAndSortingRepository<Testimonial, Long> {
}
