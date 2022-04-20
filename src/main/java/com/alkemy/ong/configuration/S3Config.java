package com.alkemy.ong.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class S3Config {

    @Value("${s3.accesskey}")
    private String accessKey;

    @Value("${s3.secretkey}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 getS3Client(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                                    .withRegion(region)
                                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                    .build();
    }

}
