package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.News;
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
    @Override
    @Transactional
    public News updateEntityIfExists(Long id, News request){

        News news = newsJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        Optional.ofNullable(request.getName()).ifPresent(news::setName);
        Optional.ofNullable(request.getContent()).ifPresent(news::setContent);
        Optional.of(request.getImage()).ifPresent(news::setImage);

        return newsJpaRepository.save(news);

    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        newsJpaRepository.findById(id).ifPresent(newsJpaRepository::delete);
    }
}
