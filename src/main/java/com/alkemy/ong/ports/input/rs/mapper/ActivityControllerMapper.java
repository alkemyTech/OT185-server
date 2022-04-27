package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityControllerMapper extends CommonMapper {

    Activity activityRequestToActivity(ActivityRequest activityRequest);
}
