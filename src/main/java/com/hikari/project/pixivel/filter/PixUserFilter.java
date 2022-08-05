package com.hikari.project.pixivel.filter;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.filter.PixInterceptorKey;
import com.hikari.commons.key.CacheKey;
import com.hikari.commons.util.JwtUtils;
import com.hikari.framework.exception.user.valid.PixUserBlackRedisException;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.pixivel.entity.PixUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * PixUserFilter
 * pixivel模块用户拦截
 * 无法被全局异常类捕捉 换为 interceptor
 * 且存在 return 后继续执行代码
 * @author lkc39miku_cn
 */
@Slf4j
//@WebFilter(urlPatterns = "/spring_cloud.pix**")
@Deprecated
public class PixUserFilter extends OncePerRequestFilter {


    @Value("${jwt.header}")
    private String token;
    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (PixInterceptorKey.IS_OK.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取token
        String token = request.getHeader(this.token);
        // 空token放行 pixUserTokenInterceptor 做处理
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 黑名单
        String blackRedis = redisCache.getCacheObject(token);
        if (StringUtils.isNotEmpty(blackRedis)) {
            log.info("black redis?: {}", blackRedis);
            throw new PixUserBlackRedisException("black token error");
        }

        // 获取用户id
        String subject;
        try {
            Claims claims = JwtUtils.parseJwt(token);
            subject = claims.getSubject();
            log.info("parse id: {}", subject);
        } catch (Exception e) {
            throw new RuntimeException("parse token error");
        }

        // 获取用户信息
        String redisKey = CacheKey.PIX_USER_KEY + subject;
        Object pixUser = redisCache.getCacheObject(redisKey);
        log.info("pixUser json: {}", pixUser);

        if (Objects.isNull(pixUser)) {
            throw new RuntimeException("check redis error staff json is null error");
        }

        PixUser staff = JSON.parseObject(pixUser.toString(), PixUser.class);
        if (Objects.isNull(staff)) {
            throw new RuntimeException("check redis error staff is null error");
        }

        filterChain.doFilter(request, response);
    }
}
