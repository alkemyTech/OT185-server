package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Activity;

public interface ActivityService {
    Long createActivity(Activity request);

    void updateActivity(Long id, Activity request);
}
