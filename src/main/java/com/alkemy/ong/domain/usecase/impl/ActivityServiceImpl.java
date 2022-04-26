package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.repository.ActivityRepository;
import com.alkemy.ong.domain.usecase.ActivityService;
import com.alkemy.ong.ports.input.rs.mapper.ActivityControllerMapper;
import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public void updateActivity(ActivityRequest request, Long id) {

        activityRepository.findById(id)
                .map(activityJpa -> {
                    activityJpa.setName(request.getName());
                    activityJpa.setContent(request.getContent());
                    activityJpa.setImage(request.getImage());
                    activityRepository.save(activityJpa);

                    return mapper.activityToActivityResponse(activityJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }
}
