package com.hikari.system.pixivel.mapper;

import com.hikari.system.pixivel.entity.PixTag;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixTagMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixTagMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixTag record);

    PixTag selectByPrimaryKey(String id);

    List<PixTag> selectByIdList(@Param(value = "id") String id);

    int updateByPrimaryKeySelective(PixTag record);

    int updateBatchSelective(List<PixTag> list);

    int batchInsert(@Param("list") List<PixTag> list);
}