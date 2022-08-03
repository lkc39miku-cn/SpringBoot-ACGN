package com.hikari.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户没有权限访问接口时的处理器
 * AccessDeniedHandlerImpl
 * @author lkc39miku_cn
 */
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * 当用户没有权限访问接口时，会调用该方法
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("请求访问：{}，授权失败，无法访问系统资源", request.getRequestURI());
        accessDeniedException.printStackTrace();
        log.error("error: {}", accessDeniedException.getMessage());
        ServletUtils.render(response, JSON.toJSONString(Result.error(com.hikari.commons.enums.HttpServletResponse.SC_FORBIDDEN)));
    }
}
