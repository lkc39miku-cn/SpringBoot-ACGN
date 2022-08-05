package com.hikari.project.pixivel.service.async;

import com.hikari.commons.util.SpringUtils;
import com.hikari.project.pixivel.service.impl.PixPictureCollectionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * PixUserCollectAsync
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Async(value = "asyncServiceExecutor")
public class PixUserCollectAsync {
    public static void refreshCollectPictureCollection(String pictureCollectionId) {
        SpringUtils.getBean(PixPictureCollectionServiceImpl.class).refreshCollect(pictureCollectionId);
    }

    public static void refreshDeleteCollectPictureCollection(String id) {
        SpringUtils.getBean(PixPictureCollectionServiceImpl.class).refreshDeleteCollect(id);
    }
}
