package com.hikari.project.pixivel.service;

import java.util.List;
import com.hikari.project.pixivel.entity.PixPictureDetailed;

/**
 * PixPictureDetailedService
 *
 * @author lkc39miku_cn
 */
public interface PixPictureDetailedService {


    int deleteByPrimaryKey(String id);

    int insert(PixPictureDetailed record);

    PixPictureDetailed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPictureDetailed record);

    int updateBatchSelective(List<PixPictureDetailed> list);

    int batchInsert(List<PixPictureDetailed> list);

}

