package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import org.mapstruct.Mapper;

@Mapper
public interface NewsControllerMapper {

   News updateNewsRequestToNews(UpdateNewsRequest newsRequest);
}
