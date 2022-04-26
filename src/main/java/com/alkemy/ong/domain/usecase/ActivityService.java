package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;

import java.util.Optional;

public interface ActivityService {
    ActivityResponse createActivity(ActivityRequest request);

    ActivityResponse updateActivity(ActivityRequest request, Long id);




}
