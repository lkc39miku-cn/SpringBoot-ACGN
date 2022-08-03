package com.hikari.system.login.service.impl;

import com.hikari.framework.redis.RedisCache;
import com.hikari.system.login.service.RegisterService;
import com.hikari.system.system.mapper.SysStaffMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RegisterServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RedisCache redisCache;

    @Resource
    private SysStaffMapper sysStaffMapper;
}
