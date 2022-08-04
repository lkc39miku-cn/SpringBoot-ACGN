package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixMapping;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixMappingMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixMappingMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixMapping record);

    PixMapping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixMapping record);

    int updateBatchSelective(List<PixMapping> list);

    int batchInsert(@Param("list") List<PixMapping> list);
}