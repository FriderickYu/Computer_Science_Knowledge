package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.ytq")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {

}
