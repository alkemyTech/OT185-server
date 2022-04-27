package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.usecase.ActivityService;
import com.alkemy.ong.ports.input.rs.mapper.ActivityControllerMapper;
import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.ACTIVITIES_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ACTIVITIES_URI)
public class ActivityController {

    private final ActivityService activityService;
    private final ActivityControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createActivity(@Valid @RequestBody ActivityRequest activityRequest) {

        Activity activity = mapper.activityRequestToActivity(activityRequest);

        final long id = activityService.createActivity(activity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
