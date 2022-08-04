package com.hikari.framework.config;

import com.hikari.project.pixivel.filter.PixUserFilter;
import com.hikari.project.pixivel.filter.PixUserTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web
 *
 * @author lkc39miku_cn
 */
@Configuration
public class Web implements WebMvcConfigurer {

    @Resource
    private PixUserTokenInterceptor pixUserTokenInterceptor;
    /**
     * addCorsMappings
     * @param registry CorsRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/book/image/");
    }

    /**
     * 跨域配置
     * security mvc
     * @param registry CorsRegistry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pixUserTokenInterceptor).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean<PixUserFilter> PixUserFilter(){
        FilterRegistrationBean<PixUserFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PixUserFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("PixUserTokenInterceptor");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
