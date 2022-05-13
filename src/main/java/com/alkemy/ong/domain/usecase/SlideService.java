package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Slide;

import java.util.List;

public interface SlideService {
    void deleteById(Long id);

    Slide getById(Long id);

    Long create(Slide slide, String imageBase64);

    List<Slide> findAll();

}
