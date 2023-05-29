package org.ytq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

// 5. 创建springmvc的配置文件, 加载controller对应的bean
@Configuration
//@ComponentScan({"org.ytq.service", "org.ytq.dao"})
@ComponentScan(value = "org.ytq", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
public class SpringConfig {


}
