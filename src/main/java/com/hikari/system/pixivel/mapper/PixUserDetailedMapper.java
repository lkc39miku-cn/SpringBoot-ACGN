package com.hikari.system.pixivel.mapper;

import com.hikari.system.pixivel.entity.PixUserDetailed;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserDetailedMapper
 *
 * @author lkc39miku_cn
 */
@Mapper
public interface PixUserDetailedMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserDetailed record);

    PixUserDetailed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserDetailed record);

    int updateBatchSelective(List<PixUserDetailed> list);

    int batchInsert(@Param("list") List<PixUserDetailed> list);
}