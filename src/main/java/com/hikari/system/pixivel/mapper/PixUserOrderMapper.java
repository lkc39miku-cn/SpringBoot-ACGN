package com.hikari.system.pixivel.mapper;

import com.hikari.system.pixivel.entity.PixUserOrder;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserOrderMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserOrder record);

    PixUserOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserOrder record);

    int updateBatchSelective(List<PixUserOrder> list);

    int batchInsert(@Param("list") List<PixUserOrder> list);
}