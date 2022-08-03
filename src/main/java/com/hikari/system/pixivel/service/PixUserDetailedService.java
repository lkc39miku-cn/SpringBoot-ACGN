package com.hikari.system.pixivel.service;

import java.util.List;
import com.hikari.system.pixivel.entity.PixUserDetailed;

/**
 * PixUserDetailedService
 *
 * @author lkc39miku_cn
 */
public interface PixUserDetailedService {


    int deleteByPrimaryKey(String id);

    int insert(PixUserDetailed record);

    PixUserDetailed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserDetailed record);

    int updateBatchSelective(List<PixUserDetailed> list);

    int batchInsert(List<PixUserDetailed> list);

    PixUserDetailed selectByAuthorId(String authorId);
}


