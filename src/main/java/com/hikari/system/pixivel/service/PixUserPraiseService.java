package com.hikari.system.pixivel.service;

import java.util.List;
import com.hikari.system.pixivel.entity.PixUserPraise;
import com.hikari.system.pixivel.entity.vo.PixUserPraiseVo;

/**
 * PixUserPraiseService
 * @author lkc39miku_cn
 */  
public interface PixUserPraiseService{


    int deleteByPrimaryKey(String id);

    int insert(PixUserPraise record);

    PixUserPraise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserPraise record);

    int updateBatchSelective(List<PixUserPraise> list);

    int batchInsert(List<PixUserPraise> list);

    PixUserPraiseVo selectList();
}
