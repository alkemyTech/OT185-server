package com.alkemy.ong.domain.usecase;


import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.News;

import java.util.List;

public interface NewsService {

    void deleteById(Long id);

    News getByIdIfExists(Long id);

    List<Comment> getCommentsByNewsId(Long id);
}
