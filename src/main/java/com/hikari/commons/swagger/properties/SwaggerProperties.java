package com.hikari.commons.swagger.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SwaggerProperties {
    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.name}")
    private String name;

    @Value("${swagger.url}")
    private String url;

    @Value("${swagger.email}")
    private String email;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.enable}")
    private Boolean enable;

    @Value("${swagger.path-mapping}")
    private String pathMapping;
}