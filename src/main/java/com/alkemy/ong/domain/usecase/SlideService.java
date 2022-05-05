package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Slide;

public interface SlideService {
    void deleteById(Long id);

    Slide getById(Long id);
}
