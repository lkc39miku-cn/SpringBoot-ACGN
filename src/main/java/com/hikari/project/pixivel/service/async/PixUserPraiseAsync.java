package com.hikari.project.pixivel.service.async;

import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.util.*;
import com.hikari.project.monitor.entity.SysLoginOs;
import com.hikari.project.monitor.service.SysLoginOsServiceImpl;
import com.hikari.project.pixivel.service.impl.PixPictureCollectionServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * PixUserPraiseAsync
 *
 * @author lkc39miku_cn
 */
@Slf4j
public class PixUserPraiseAsync {

    public static TimerTask refreshPraisePictureCollection(String id) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(PixPictureCollectionServiceImpl.class).refreshPraise(id);
            }
        };
    }
}
