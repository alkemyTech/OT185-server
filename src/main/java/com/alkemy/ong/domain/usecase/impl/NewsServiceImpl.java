package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.repository.CategoryRepository;
import com.alkemy.ong.domain.repository.NewsRepository;
import com.alkemy.ong.domain.usecase.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsJpaRepository;
    private final CategoryRepository categoryJpaRepository;

    private static Long newCategoryId = Long.valueOf(6);



    @Override
    @Transactional
    public Long createEntity(News news, Long CategoryId){

         Category foundCategory = categoryJpaRepository.findById(newCategoryId)
                 .orElseThrow(() -> new UsernameNotFoundException("Category id: %s not found".formatted(newCategoryId)));

                news.setCategory(foundCategory);

                return newsJpaRepository.save(news).getId();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newsJpaRepository.findById(id).ifPresent(newsJpaRepository::delete);
    }
}
