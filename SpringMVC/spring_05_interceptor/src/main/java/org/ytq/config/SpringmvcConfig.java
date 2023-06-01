package org.ytq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.ytq.controller.interceptor.ProjectInterceptor;
import org.ytq.controller.interceptor.ProjectInterceptor2;

@Configuration
@ComponentScan("org.ytq.controller")
@EnableWebMvc

// 实现WebMvcConfigurer可以简化开发，但有一定的侵入性
public class SpringmvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private ProjectInterceptor2 projectInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
