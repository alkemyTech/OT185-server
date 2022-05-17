package com.alkemy.ong.domain.usecase.impl;


import com.alkemy.ong.common.exception.NotFoundException;

import com.alkemy.ong.domain.model.*;

import com.alkemy.ong.domain.repository.CategoryRepository;
import com.alkemy.ong.domain.repository.NewsRepository;
import com.alkemy.ong.domain.usecase.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsJpaRepository;
    private final CategoryRepository categoryJpaRepository;

    private static final Long GENERAL_NEW_ID = Long.valueOf(6);



    @Override
    @Transactional
    public Long createEntity(News news){

        Category category  = categoryJpaRepository.findById(categoryId)
                .orElseGet(() -> categoryJpaRepository.findById(GENERAL_NEW_ID).get());

        news.setCategory(category);

                return newsJpaRepository.save(news).getId();
    }

    @Override
    @Transactional
    public News updateEntityIfExists(Long id, News request, Long categoryId){

        News news = newsJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Category category = categoryJpaRepository.findById(categoryId).orElseThrow(()
                -> new NotFoundException(request.getCategory().getId()));

        news.setName(request.getName());
        news.setContent(request.getContent());
        news.setImage(request.getImage());
        news.setCategory(category);

        return newsJpaRepository.save(news);

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

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByNewsId(Long id) {
        News news = getByIdIfExists(id);
        return news.getComments();
    }

    @Override
    @Transactional(readOnly = true)
    public NewsList getList(PageRequest pageRequest) {
        Page<News> page = newsJpaRepository.findAll(pageRequest);
        return new NewsList(page.getContent(), pageRequest, page.getTotalElements());
    }
}
