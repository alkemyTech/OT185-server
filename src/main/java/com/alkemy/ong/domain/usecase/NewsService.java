package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.News;
import org.springframework.http.ResponseEntity;

public interface NewsService {

    News updateEntityIfExists(Long id, News news);

    void deleteById(Long id);
}
