package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// 5. 创建springmvc的配置文件, 加载controller对应的bean
@Configuration
@ComponentScan("org.ytq.controller")
// 将json转换为对象
@EnableWebMvc
public class SpringmvcConfig {

}
