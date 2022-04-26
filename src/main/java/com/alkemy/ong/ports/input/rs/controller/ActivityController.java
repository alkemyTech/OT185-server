package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.ports.input.rs.request.ActivityRequest;
import com.amazonaws.services.pinpoint.model.ActivitiesResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.ACTIVITIES_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ACTIVITIES_URI)
public class ActivityController {

    @PostMapping
    public ResponseEntity<ActivitiesResponse> createActivity(@Valid @RequestBody ActivityRequest ActivityReq){

        


        return ResponseEntity<?>(,HttpStatus.CREATED)
    }



}
