package com.hikari.project.system.service;

import com.hikari.project.system.entity.SysConfig;
import java.util.List;
    
/**
 * SysConfigService
 * @author lkc39miku_cn
 */  
public interface SysConfigService{


    int deleteByPrimaryKey(String id);

    int insert(SysConfig record);

    SysConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateBatchSelective(List<SysConfig> list);

    int batchInsert(List<SysConfig> list);

    List<SysConfig> selectList(SysConfig sysConfig);

    int deleteBatch(List<String> ids);

    boolean checkConfigName(String name);

    boolean checkConfigKey(String configKey);

    boolean checkConfigName(String name, String id);

    boolean checkConfigKey(String configKey, String id);

    List<SysConfig> selectNotList(SysConfig sysConfig);

    SysConfig selectCaptchaOnOff();

    void loadingConfigCache();
}
