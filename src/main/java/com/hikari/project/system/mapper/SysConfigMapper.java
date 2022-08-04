package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysConfig;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysConfigMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysConfig record);

    SysConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateBatchSelective(List<SysConfig> list);

    int batchInsert(@Param("list") List<SysConfig> list);

    List<SysConfig> selectList(SysConfig sysConfig);

    int deleteBatch(List<String> ids);

    List<SysConfig> selectNotList(SysConfig sysConfig);

    SysConfig selectCaptchaOnOff();
}