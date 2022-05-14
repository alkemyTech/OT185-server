package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Slide;
import com.alkemy.ong.ports.input.rs.request.SlideRequest;
import com.alkemy.ong.ports.input.rs.response.SlideResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface SlideControllerMapper extends CommonMapper {

    @IterableMapping(qualifiedByName = "slideToSlideResponse")
    List<SlideResponse> slideListToSlideResponseList(List<Slide> slides);

    @Named("slideToSlideResponse")
    SlideResponse slideToSlideResponse(Slide slide);

    @Mapping(target = "organization.id", source = "organization")
    Slide createSlideRequestToSlide(SlideRequest create);
}
