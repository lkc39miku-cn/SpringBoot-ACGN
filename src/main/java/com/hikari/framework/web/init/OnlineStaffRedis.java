package com.hikari.framework.web.init;

import com.hikari.commons.key.CacheKey;
import com.hikari.framework.redis.RedisCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * OnlineStaffRedis
 * @author lkc39miku_cn
 */
@Component
public class OnlineStaffRedis {

    @Resource
    private RedisCache redisCache;

    @PostConstruct
    public void onlineStaffRedis() {
        if (Objects.nonNull(redisCache.getCacheObject(CacheKey.ONLINE_STAFF_KEY))) {
            redisCache.deleteObject(CacheKey.ONLINE_STAFF_KEY);
        }
        redisCache.setCacheObject(CacheKey.ONLINE_STAFF_KEY, 0);
    }
}
