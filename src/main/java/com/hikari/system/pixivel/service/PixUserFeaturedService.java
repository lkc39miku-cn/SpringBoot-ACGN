package com.hikari.system.pixivel.service;

import java.util.List;
import com.hikari.system.pixivel.entity.PixUserFeatured;
    
/**
 * PixUserFeaturedService
 * @author lkc39miku_cn
 */  
public interface PixUserFeaturedService{


    int deleteByPrimaryKey(String id);

    int insert(PixUserFeatured record);

    PixUserFeatured selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserFeatured record);

    int updateBatchSelective(List<PixUserFeatured> list);

    int batchInsert(List<PixUserFeatured> list);

}
