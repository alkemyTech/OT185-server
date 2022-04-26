package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.usecase.ActivityService;
import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.alkemy.ong.ports.input.rs.response.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.ACTIVITIES_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ACTIVITIES_URI)
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@Valid @RequestBody ActivityRequest activityRequest) {

        ActivityResponse response = activityService.createActivity(activityRequest);

        return new ResponseEntity<ActivityResponse>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActivity(@PathVariable Long id,
                               @Valid @RequestBody ActivityRequest activityRequest) {
        activityService.updateActivity(activityRequest, id);
    }


}
