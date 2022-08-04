package com.hikari.project.pixivel.service;

import com.hikari.project.pixivel.entity.PixMapping;
import java.util.List;
    
/**
 * PixMappingService
 * @author lkc39miku_cn
 */  
public interface PixMappingService{


    int deleteByPrimaryKey(String id);

    int insert(PixMapping record);

    PixMapping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixMapping record);

    int updateBatchSelective(List<PixMapping> list);

    int batchInsert(List<PixMapping> list);

}
