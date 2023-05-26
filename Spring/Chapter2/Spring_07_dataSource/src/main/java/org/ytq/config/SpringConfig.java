package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("org.ytq")

@Import({JDBCConfig.class})
public class SpringConfig {

}
