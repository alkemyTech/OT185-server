package com.alkemy.ong.domain.usecase;



import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.NewsList;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface NewsService {

    News updateEntityIfExists(Long id, News news, Long categoryId);

    void deleteById(Long id);

    Long createEntity(News news);

    News getByIdIfExists(Long id);

    List<Comment> getCommentsByNewsId(Long id);

    NewsList getList(PageRequest pageRequest);

}
