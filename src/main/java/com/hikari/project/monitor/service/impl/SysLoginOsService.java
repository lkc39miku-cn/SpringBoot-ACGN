package com.hikari.project.monitor.service.impl;

import java.util.List;
import com.hikari.project.monitor.entity.SysLoginOs;
    
/**
 * SysLoginOsService
 * @author lkc39miku_cn
 */  
public interface SysLoginOsService{


    int deleteByPrimaryKey(String id);

    int insert(SysLoginOs record);

    SysLoginOs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLoginOs record);

    int updateBatchSelective(List<SysLoginOs> list);

    int batchInsert(List<SysLoginOs> list);

}
