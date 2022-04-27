package com.alkemy.ong.domain.usecase.impl;

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
    public void deleteById(Long id) {
        newsJpaRepository.findById(id).ifPresent(newsJpaRepository::delete);
    }
}
