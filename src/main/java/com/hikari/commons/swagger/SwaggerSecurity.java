package com.hikari.commons.swagger;

import com.hikari.commons.key.TokenKey;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerSecurity
 * jakarta not support swagger (javax)
 * @author lkc39miku_cn
 */
@Configuration
public class SwaggerSecurity {
    public List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(TokenKey.TOKEN, TokenKey.AUTHORIZATION, In.HEADER.toValue()));
        return apiKeyList;
    }

    public List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(securityReferences())
                        .operationSelector(v -> v.requestMappingPattern().matches("/.*"))
                        .build()
        );
        return securityContexts;
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference(TokenKey.AUTHORIZATION, authorizationScopes));
        return securityReferences;
    }
}
