package com.hikari.system.pixivel.mapper;

import com.hikari.system.pixivel.entity.PixUserCollect;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserCollectMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserCollectMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserCollect record);

    PixUserCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserCollect record);

    int updateBatchSelective(List<PixUserCollect> list);

    int batchInsert(@Param("list") List<PixUserCollect> list);

    List<PixUserCollect> selectList(PixUserCollect pixUserCollect);
}