package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import org.mapstruct.Mapper;

@Mapper
public interface NewsControllerMapper {

    News createNewsRequestToNews(CreateNewsRequest createNewsRequest);
}

