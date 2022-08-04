package com.hikari.project.pixivel.service;

import java.util.List;
import com.hikari.project.pixivel.entity.PixUserPrimary;
    
/**
 * PixUserPrimaryService
 * @author lkc39miku_cn
 */  
public interface PixUserPrimaryService{


    int deleteByPrimaryKey(String id);

    int insert(PixUserPrimary record);

    PixUserPrimary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserPrimary record);

    int updateBatchSelective(List<PixUserPrimary> list);

    int batchInsert(List<PixUserPrimary> list);

}
