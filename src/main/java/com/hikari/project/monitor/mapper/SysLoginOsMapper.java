package com.hikari.project.monitor.mapper;

import com.hikari.project.monitor.entity.SysLoginOs;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysLoginOsMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysLoginOsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLoginOs record);

    SysLoginOs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLoginOs record);

    int updateBatchSelective(List<SysLoginOs> list);

    int batchInsert(@Param("list") List<SysLoginOs> list);
}