package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.ports.output.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;


@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class BucketController {
    private final AmazonS3Client awss3Service;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file) {
        String url = awss3Service.uploadFile(file);
        URI location = URI.create(url);
        return ResponseEntity.created(location).build();
    }
}
