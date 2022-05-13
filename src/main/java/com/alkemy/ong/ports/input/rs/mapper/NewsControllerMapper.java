package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface NewsControllerMapper {

    @IterableMapping(qualifiedByName = "newsToNewsResponse")
    List<NewsResponse> newsListToNewsResponseList(List<News> news);

    News createNewsRequestToNews(CreateNewsRequest createNewsRequest);

    News updateNewsRequestToNews(UpdateNewsRequest newsRequest);
    NewsResponse newsToNewsResponse(News newsResponse);

}

