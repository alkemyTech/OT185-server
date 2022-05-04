package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.repository.NewsRepository;
import com.alkemy.ong.domain.usecase.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsJpaRepository;
    @Override
    @Transactional
    public News updateEntityIfExists(Long id, News request){

        News news = newsJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        news.setName(request.getName());
        news.setContent(request.getContent());
        news.setImage(request.getImage());

        return newsJpaRepository.save(news);

    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        newsJpaRepository.findById(id).ifPresent(newsJpaRepository::delete);
    }
}
