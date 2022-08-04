package com.hikari.project.pixivel.service;

import com.hikari.project.pixivel.entity.PixPicture;

import java.util.List;

/**
 * PixPictureService
 *
 * @author lkc39miku_cn
 */
public interface PixPictureService {


    int deleteByPrimaryKey(String id);

    int insert(PixPicture record);

    PixPicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPicture record);

    int updateBatchSelective(List<PixPicture> list);

    int batchInsert(List<PixPicture> list);

}

