package com.alkemy.ong;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@TestConfiguration
@EnableJpaRepositories
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.alkemy.ong.domain")
public class H2Config {

    @Bean
    public DataSource dataSourceWithEmbeddedDatabaseBuilder() {
        return new EmbeddedDatabaseBuilder()
                .setName("test")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
