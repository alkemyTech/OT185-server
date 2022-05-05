package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.domain.repository.SlideRepository;
import com.alkemy.ong.domain.usecase.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideJpaRepository;

    @Override
    @Transactional
    public void deleteById(Long id) {
        slideJpaRepository.findById(id).ifPresent(slideJpaRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Slide getById(Long id) {
        return slideJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
