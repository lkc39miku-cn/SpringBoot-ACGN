package com.hikari.framework.security.filter;

import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.login.service.impl.TokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JwtAuthenticationToken
 * @author lkc39miku_cn
 */
@Slf4j
@Component
public class JwtAuthenticationToken extends OncePerRequestFilter {
    @Resource
    private TokenServiceImpl tokenServiceImpl;

    /**
     * 当用户认证失败时，会调用该方法
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param filterChain filter chain
     * @throws ServletException servlet exception
     * @throws IOException io exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LoginStaff loginStaff = tokenServiceImpl.getLoginStaff(request);
        if (Objects.nonNull(loginStaff) && Objects.isNull(SecurityUtils.getAuthentication())) {
            tokenServiceImpl.verifyToken(loginStaff);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginStaff, null, loginStaff.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
