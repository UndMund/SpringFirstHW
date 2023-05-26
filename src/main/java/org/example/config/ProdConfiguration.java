package org.example.config;

import org.example.utils.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile("prod")
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example")
public class ProdConfiguration {
}
