package com.hikari.framework.init;

import com.hikari.framework.redis.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Runner
 *
 * @author lkc39miku_cn
 */
@Component
public class Runner implements CommandLineRunner {

    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

    }
}
