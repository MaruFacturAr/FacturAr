package com.facturar.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.facturar.app.repository")
@EnableAutoConfiguration
@EntityScan("com.facturar.app.entity")
public class PersistenceJPAConfig {
}
