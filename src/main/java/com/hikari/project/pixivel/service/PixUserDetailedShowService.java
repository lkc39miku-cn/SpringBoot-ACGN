package com.hikari.project.pixivel.service;

import java.util.List;
import com.hikari.project.pixivel.entity.PixUserDetailedShow;

/**
 * PixUserDetailedShowService
 *
 * @author lkc39miku_cn
 */
public interface PixUserDetailedShowService {


    int deleteByPrimaryKey(String id);

    int insert(PixUserDetailedShow record);

    PixUserDetailedShow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserDetailedShow record);

    int updateBatchSelective(List<PixUserDetailedShow> list);

    int batchInsert(List<PixUserDetailedShow> list);

    PixUserDetailedShow selectByAuthorId(String authorId);
}


