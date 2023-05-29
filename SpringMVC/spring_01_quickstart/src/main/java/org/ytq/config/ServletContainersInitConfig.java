package org.ytq.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

// 6. 定义一个servlet容器启动的配置类, 在里面加载Spring的配置
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
    // 加载springmvc容器配置
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringmvcConfig.class);
        return ctx;
    }

    // 设置哪些请求归属springmvc处理
    protected String[] getServletMappings() {
        // 所有请求都归springmvc处理
        return new String[]{"/"};
    }

    // 加载spring容器配置
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfig.class);
        return ctx;
    }



    // 乱码处理, 过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
