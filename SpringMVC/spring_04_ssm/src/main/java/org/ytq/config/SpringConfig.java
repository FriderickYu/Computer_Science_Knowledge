package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"org.ytq.service"})
@PropertySource("classpath:jdbc.properties")
@Import({jdbcConfig.class, MybatisConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}
