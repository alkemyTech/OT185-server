package com.alkemy.ong.domain.usecase.impl;


import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.repository.CategoryRepository;
import com.alkemy.ong.domain.repository.NewsRepository;
import com.alkemy.ong.domain.usecase.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsJpaRepository;
    private final CategoryRepository categoryJpaRepository;

    private static Long newCategoryId = Long.valueOf(6);



    @Override
    @Transactional
    public Long createEntity(News news, Long CategoryId){

         Optional<Category> foundCategory = categoryJpaRepository.findById(newCategoryId);

                news.setCategory(foundCategory.get());

                return newsJpaRepository.save(news).getId();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newsJpaRepository.findById(id).ifPresent(newsJpaRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public News getByIdIfExists(Long id) {
        return newsJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
