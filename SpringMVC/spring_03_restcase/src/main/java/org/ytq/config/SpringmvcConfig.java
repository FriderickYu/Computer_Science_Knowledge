package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"org.ytq.controller", "org.ytq.config"})
// 将json转换为对象
@EnableWebMvc
public class SpringmvcConfig {

}