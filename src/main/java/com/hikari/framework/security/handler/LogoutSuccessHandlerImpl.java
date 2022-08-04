package com.hikari.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.ServletUtils;
import com.hikari.framework.manager.AsyncManager;
import com.hikari.framework.manager.factory.AsyncFactory;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.login.service.impl.TokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * LogoutSuccessHandlerImpl
 * @author lkc39miku_cn
 */
@Slf4j
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Resource
    private TokenServiceImpl tokenServiceImpl;

    /**
     * 当用户认证失败时，会调用该方法
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authentication that caused the invocation
     * @throws IOException io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginStaff staff = tokenServiceImpl.getLoginStaff(request);
        if (Objects.nonNull(staff)) {
            String username = staff.getUsername();
            tokenServiceImpl.deleteLoginStaff(staff.getToken());
            AsyncManager.me().execute(AsyncFactory.recolonising(username, SysStaffKey.LOGOUT, "退出成功"));
        }
        ServletUtils.render(response, JSON.toJSONString(Result.success("退出成功")));
    }
}
