package com.alkemy.ong.configuration;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${email.sendgrid.apikey}")
    private String apiKey;

    @Bean
    SendGrid sendGridClient() {
        return new SendGrid(apiKey);
    }
}
