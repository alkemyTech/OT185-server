package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.repository.TestimonialRepository;
import com.alkemy.ong.domain.usecase.TestimonialService;

import javax.transaction.Transactional;

public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Override
    @Transactional
    public void deleteById(Long id){
        testimonialRepository.findById(id).ifPresent(testimonialRepository::delete);
    }

}
