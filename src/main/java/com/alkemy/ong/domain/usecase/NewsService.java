package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.News;

public interface NewsService {

    void deleteById(Long id);

    Long createEntity(News news, Long CategoryId);
}
