package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.News;

import java.util.List;


public interface NewsService {

    News updateEntityIfExists(Long id, News news, Long categoryId);

    void deleteById(Long id);

    Long createEntity(News news, Long categoryId);

    News getByIdIfExists(Long id);

    List<Comment> getCommentsByNewsId(Long id);

}
