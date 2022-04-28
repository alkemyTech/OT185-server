package com.alkemy.ong.domain.repository.usecase.impl;

import com.alkemy.ong.domain.repository.TestimonialRepository;
import com.alkemy.ong.domain.repository.usecase.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Override
    @Transactional
    public void deleteById(Long id){
        testimonialRepository.findById(id).ifPresent(testimonialRepository::delete);
    }

}
