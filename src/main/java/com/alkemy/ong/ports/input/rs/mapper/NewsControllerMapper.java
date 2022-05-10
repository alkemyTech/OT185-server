package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import org.mapstruct.Mapper;

@Mapper(uses = {CategoryControllerMapper.class})
public interface NewsControllerMapper {


    NewsResponse newsToNewsResponse(News newsResponse);
}