package com.hikari.system.pixivel.service;

import java.util.List;
import com.hikari.system.pixivel.entity.PixUserOrder;
    
/**
 * PixUserOrderService
 * @author lkc39miku_cn
 */  
public interface PixUserOrderService{


    int deleteByPrimaryKey(String id);

    int insert(PixUserOrder record);

    PixUserOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserOrder record);

    int updateBatchSelective(List<PixUserOrder> list);

    int batchInsert(List<PixUserOrder> list);

}
