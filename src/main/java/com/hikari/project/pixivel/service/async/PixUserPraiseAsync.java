package com.hikari.project.pixivel.service.async;

import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.util.*;
import com.hikari.project.monitor.entity.SysLoginOs;
import com.hikari.project.monitor.service.SysLoginOsServiceImpl;
import com.hikari.project.pixivel.service.impl.PixPictureCollectionServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * PixUserPraiseAsync
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Async(value = "asyncServiceExecutor")
public class PixUserPraiseAsync {
    /**
     * 刷新用户点赞的图片集合的点赞数量
     * @param id 图片集合id
     */
    public static void refreshPraisePictureCollection(String id) {
        SpringUtils.getBean(PixPictureCollectionServiceImpl.class).refreshPraise(id);
    }

    public static void refreshDeletePraisePictureCollection(String id) {
        SpringUtils.getBean(PixPictureCollectionServiceImpl.class).refreshDeletePraise(id);
    }
}
