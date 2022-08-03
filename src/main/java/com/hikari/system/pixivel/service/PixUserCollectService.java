package com.hikari.system.pixivel.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hikari.system.pixivel.entity.PixUserCollect;
    
/**
 * PixUserCollectService
 * @author lkc39miku_cn
 */  
public interface PixUserCollectService{


    int deleteByPrimaryKey(String id);

    int insert(PixUserCollect record);

    PixUserCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserCollect record);

    int updateBatchSelective(List<PixUserCollect> list);

    int batchInsert(List<PixUserCollect> list);

    PageInfo<PixUserCollect> selectList(PixUserCollect pixUserCollect);

    List<String> selectTag();

    int updateTagBatch(PixUserCollect pixUserCollect);
}
