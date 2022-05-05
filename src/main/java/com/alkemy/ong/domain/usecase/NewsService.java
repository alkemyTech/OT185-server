package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.News;

public interface NewsService {

    void deleteById(Long id);

    News getByIdIfExists(Long id);
}
