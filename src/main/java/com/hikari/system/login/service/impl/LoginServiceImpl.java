package com.hikari.system.login.service.impl;

import com.hikari.framework.redis.RedisCache;
import com.hikari.system.login.entity.LoginBody;
import com.hikari.system.login.service.LoginService;
import com.hikari.system.system.mapper.SysConfigMapper;
import com.hikari.system.system.mapper.SysStaffMapper;
import com.hikari.system.system.service.impl.SysConfigServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * LoginServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Resource
    private SysStaffMapper sysStaffMapper;

    @Override
    public String login(LoginBody loginBody) {
        return null;
    }
}
