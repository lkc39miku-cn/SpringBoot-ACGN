package com.hikari.project.pixivel.service;

import com.hikari.project.pixivel.entity.PixTag;
import java.util.List;
    
/**
 * PixTagService
 * @author lkc39miku_cn
 */  
public interface PixTagService{


    int deleteByPrimaryKey(String id);

    int insert(PixTag record);

    PixTag selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixTag record);

    int updateBatchSelective(List<PixTag> list);

    int batchInsert(List<PixTag> list);

    List<PixTag> selectList(PixTag pixTag);
}
