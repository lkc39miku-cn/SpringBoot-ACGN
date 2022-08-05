package com.hikari.project.pixivel.filter;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.key.CacheKey;
import com.hikari.commons.util.JwtUtils;
import com.hikari.framework.exception.user.valid.PixUserBlackRedisException;
import com.hikari.framework.exception.user.valid.PixUserNotLoginException;
import com.hikari.framework.redis.RedisCache;
import com.hikari.framework.thread.ThreadLocalInfo;
import com.hikari.project.pixivel.entity.PixUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * PixUserTokenCatchFilter
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Component
public class PixUserTokenInterceptor implements HandlerInterceptor {

    @Value("${jwt.header}")
    private String token;
    @Resource
    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(this.token);

        if (StringUtils.isEmpty(token)) {
            throw new PixUserNotLoginException("not login");
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

        PixUser pixUserVo = JSON.parseObject(pixUser.toString(), PixUser.class);
        if (Objects.isNull(pixUserVo)) {
            throw new RuntimeException("check redis error staff is null error");
        }
        log.info("PixUser interceptor: {}", pixUserVo);

        ThreadLocalInfo.set(pixUserVo);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalInfo.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
