package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.ports.input.rs.response.SlideResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface SlideControllerMapper extends CommonMapper {

    @Named("slideToSlideResponse")
    SlideResponse slideToSlideResponse(Slide slide);
}
