package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.News;


public interface NewsService {

    News updateEntityIfExists(Long id, News news);

    void deleteById(Long id);

    News getByIdIfExists(Long id);
}
