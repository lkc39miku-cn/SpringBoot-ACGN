package com.hikari.framework.config;

import com.hikari.commons.filter.PixInterceptorKey;
import com.hikari.project.pixivel.filter.PixUserTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * Web
 *
 * @author lkc39miku_cn
 */
@Slf4j
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
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pixUserTokenInterceptor).addPathPatterns(List.copyOf(PixInterceptorKey.IS_OFF))
                .excludePathPatterns(List.copyOf(PixInterceptorKey.IS_OK));
    }
}
