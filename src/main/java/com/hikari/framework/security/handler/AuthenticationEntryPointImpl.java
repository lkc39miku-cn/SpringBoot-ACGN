package com.hikari.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 用户认证失败处理器
 * AuthenticationEntryPointImpl
 * @author lkc39miku_cn
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     * 当用户认证失败时，会调用该方法
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        authException.printStackTrace();
        log.error("error: {}", authException.getMessage());

        if (Objects.isNull(authException.getCause())) {
            ServletUtils.render(response, JSON.toJSONString(Result.error(com.hikari.commons.enums.HttpServletResponse.SC_UNAUTHORIZED)));
        }
        ServletUtils.render(response, JSON.toJSONString(Result.error(authException.getCause().getMessage())));
    }
}
