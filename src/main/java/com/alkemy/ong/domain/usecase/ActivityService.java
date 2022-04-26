package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;

public interface ActivityService {
    ActivityResponse createActivity(ActivityRequest request);

    void updateActivity(ActivityRequest request, Long id);


}
