package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixUserPrimary;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserPrimaryMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserPrimaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserPrimary record);

    PixUserPrimary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserPrimary record);

    int updateBatchSelective(List<PixUserPrimary> list);

    int batchInsert(@Param("list") List<PixUserPrimary> list);
}