package com.hikari.project.pixivel.service.impl;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.key.CacheKey;
import com.hikari.commons.util.JwtUtils;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.framework.exception.user.valid.PixUserExistException;
import com.hikari.framework.exception.user.valid.PixUserPasswordNotMatchException;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.pixivel.mapper.PixUserMapper;
import com.hikari.project.pixivel.service.PixLoginService;
import com.hikari.project.user.entity.User;
import com.hikari.project.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * PixLoginServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
@Slf4j
public class PixLoginServiceImpl implements PixLoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private PixUserMapper pixUserMapper;

    @Override
    public String login(User user) {

        User loginUser = userMapper.selectByUsername(user.getUsername());
        if (Objects.isNull(loginUser)) {
            throw new PixUserExistException("用户不存在");
        }

        if (!SecurityUtils.matchesPassword(user.getPassword(), loginUser.getPassword())) {
            throw new PixUserPasswordNotMatchException("密码错误");
        }

        String id = user.getId();
        Object json = redisCache.getCacheObject(CacheKey.PIX_USER_KEY + id);

        if (Objects.nonNull(json)) {
            // json 存在判断是否可转换为 PixUser 对象
            PixUser pixUser = JSON.parseObject(json.toString(), PixUser.class);
        }
        // 创建 jwt token
        String jwt = JwtUtils.createJwt(id);

        // user -> pixUser
        PixUser pixUser = pixUserMapper.selectByUserId(id);
        if (Objects.isNull(pixUser)) {
            throw new PixUserExistException("用户不存在");
        }

        redisCache.setCacheObject(CacheKey.PIX_USER_KEY + id, pixUser);
        log.info("token: {}", jwt);
        log.info("login: {}, {}", id, pixUser);


        Integer object = redisCache.getCacheObject(CacheKey.PIX_USER_ONLINE);
        if (Objects.isNull(object)) {
            redisCache.setCacheObject(CacheKey.PIX_USER_ONLINE, 0);
        }
        redisCache.setCacheObject(CacheKey.PIX_USER_ONLINE, ++object);
        return jwt;
    }

    @Override
    public String logout(HttpServletRequest request) {
        return null;
    }
}
