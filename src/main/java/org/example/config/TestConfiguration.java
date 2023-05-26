package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("test")
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan(basePackages = "org.example")
public class TestConfiguration {
}
