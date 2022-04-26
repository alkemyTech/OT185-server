package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.repository.ActivityRepository;
import com.alkemy.ong.domain.usecase.ActivityService;
import com.alkemy.ong.ports.input.rs.mapper.ActivityControllerMapper;
import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityControllerMapper mapper;

    @Override
    public ActivityResponse createActivity(ActivityRequest request) {

        Activity activity = mapper.activityRequestToActivity(request);

        activityRepository.save(activity);

        return mapper.activityToActivityResponse(activity);
    }
}
