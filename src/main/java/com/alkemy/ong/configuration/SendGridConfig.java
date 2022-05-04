package com.alkemy.ong.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.sendgrid.SendGrid;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class SendGridConfig {

    @Value("${email.sendgrid.apikey}")
    private String apiKey;

    @Bean
    SendGrid sendGridClient() {
        return new SendGrid(apiKey);
    }
}
