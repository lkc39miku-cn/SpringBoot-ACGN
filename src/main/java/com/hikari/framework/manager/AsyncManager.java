package com.hikari.framework.manager;

import com.hikari.commons.util.SpringUtils;
import com.hikari.commons.util.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * AsyncManager
 * @author lkc39miku_cn
 */
@Component
public class AsyncManager {
    @Value("${async-manager.operate-delay-time}")
    private Integer OPERATE_DELAY_TIME = 10;
    /**
     * 异步操作任务线程池
     */
    private final ScheduledExecutorService scheduledExecutorService = SpringUtils.getBean("scheduledExecutorService");
    private static final AsyncManager ME = new AsyncManager();

    public static AsyncManager me() {
        return ME;
    }

    public void execute(TimerTask timerTask) {
        scheduledExecutorService.schedule(timerTask, OPERATE_DELAY_TIME, TimeUnit.SECONDS);
    }

    public void shutDown() {
        ThreadPoolUtils.shutdownAndAwaitTermination(scheduledExecutorService);
    }
}