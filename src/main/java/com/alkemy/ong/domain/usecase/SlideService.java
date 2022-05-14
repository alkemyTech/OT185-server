package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.domain.model.SlideList;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SlideService {
    void deleteById(Long id);

    Slide getById(Long id);

    Long create(Slide slide, String imageBase64);

    List<Slide> findAll();

    SlideList getList(PageRequest pageRequest);
}
