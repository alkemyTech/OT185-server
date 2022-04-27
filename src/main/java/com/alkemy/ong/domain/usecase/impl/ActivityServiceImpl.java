package com.alkemy.ong.domain.usecase.impl;

<<<<<<< HEAD
import com.alkemy.ong.common.exception.NotFoundException;
=======
>>>>>>> main
import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.repository.ActivityRepository;
import com.alkemy.ong.domain.usecase.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public Long createActivity(Activity request) {

        return activityRepository.save(request).getId();
    }


    @Override
    public void updateActivity(Activity request, Long id) {

        Activity activity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        activity.setName(request.getName());
        activity.setContent(request.getContent());
        if (request.getImage() != null) {
            activity.setImage(request.getImage());
        }
        activityRepository.save(activity);
    }

}
