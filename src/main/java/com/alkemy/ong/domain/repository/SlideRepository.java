package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Slide;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SlideRepository extends PagingAndSortingRepository<Slide, Long> {
    Optional<Slide> findByOrder(Integer order);
}
